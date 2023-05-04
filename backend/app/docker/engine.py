"""Docker engine class. Contains all docker logic"""
import json
import docker

client = docker.from_env()


def init_docker():
    """Method for init application Docker"""

    try:
        client.swarm.reload()

        return client.swarm.attrs
    except docker.errors.APIError:
        return "This node is not a part of Docker swarm, please init a swarm mode or join"


def image_pull(image_name):
    """Method for pull an image from Docker hub"""

    print(f'Pulling image: {image_name}')
    resp = client.api.pull(image_name, stream=True, decode=True)
    for line in resp:
        print(json.dumps(line['status'], indent=4))
        if 'progress' in line:
            print(json.dumps(line['progress'], indent=3))


def create_service(workspace, user):
    """Method to deploy new service inside swarm"""

    try:
        name = workspace['name'].replace(' ', '_')
        service = client.services.create(image='linuxserver/code-server', command=None, name=name,
                                         labels={
                                             f'service-{user["name"]}': name,
                                             'service-description': workspace['description']
                                         },
                                         mode='global',
                                         env=['PUID=1000', 'PGID=1000',
                                              'TZ=Etc/UTC', 'PASSWORD=vitidev'],
                                         endpoint_spec=docker.types.EndpointSpec(ports={workspace['port']: 8443}))

        service.reload()

        return service.attrs

    except docker.errors.APIError as error:
        print('Something goes wrong')
        print('error', error)
        return None


def get_services_by_label(label, url):
    """Method to get all services runnung by labels"""

    try:
        result = []
        for service in client.services.list(filters={'label': f'service-{label}'}):
            workspace = {'name': service.attrs['Spec']['Name'],
                         'url': f'{url}:{service.attrs["Endpoint"]["Ports"][0]["PublishedPort"]}',
                         'template': service.attrs['Spec']['TaskTemplate']['ContainerSpec']['Image'],
                         'description': service.attrs['Spec']["Labels"]['service-description'],
                         'env': service.attrs['Spec']['TaskTemplate']['ContainerSpec']['Env']}
            result.append(workspace)

        return result
    except docker.errors.APIError as error:
        print('error', error)
        return None

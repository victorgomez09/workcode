"""Docker engine class. Contains all docker logic"""
import json
import docker

client = docker.from_env()


def init_swarm():
    """Method for init Docker swarm mode"""
    try:
        client.swarm.init()
        client.swarm.reload()

        return "Docker swarm mode created"
    except docker.errors.APIError:
        client.swarm.leave(force=True)
        return init_swarm()


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
                               labels={name: user['email']}, 
                               mode='global',
                               env=['PUID=1000', 'PGID=1000', 'TZ=Etc/UTC', 'PASSWORD=vitidev'],
                               endpoint_spec=docker.types.EndpointSpec(ports={1209: 8443}))

        service.reload()

        return service.attrs

    except docker.errors.APIError as error:
        print('Something goes wrong')
        print('error', error)

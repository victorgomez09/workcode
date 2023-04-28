import { Link } from "react-router-dom";
import { useQuery } from "react-query";

import { getWorkspaces } from "../service/workspace.service";
import useAuthStore from "../store/user.store";

export default function Dashboard() {
    const [token] = useAuthStore(state => [state.token])
    const { data, isLoading, error } = useQuery('get-workspaces', () => getWorkspaces());

    if (isLoading) return <span>Loading...</span>
    if (error) return <p>error</p>

    return (
        <div className="flex flex-col flex-1">
            <div className="">
                <h4 className="text-lg">Workspaces</h4>
                <span>Create a workspace from a <Link to="/templates">Template</Link></span>
            </div>

            <div className="overflow-x-auto">
                <table className="table w-full">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Template</th>
                            <th>Description</th>
                            <th>Url</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data?.map((workspace, index) => {
                            return (
                                <tr key={index}>
                                    <td>{workspace.name}</td>
                                    <td>{workspace.image}</td>
                                    <td>{workspace.description}</td>
                                    <td>{workspace.url}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
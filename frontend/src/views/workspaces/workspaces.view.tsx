import { Link } from "react-router-dom";

import { getWorkspaces } from "../../service/workspace.service";
import useAuthStore from "../../store/user.store";
import { WorkspaceModal } from "../../components/workspace-modal.component";
import { Loading } from "../../components/loading.component";
import { useCustomQuery } from "../../hooks/query.hook";

export default function Workspaces() {
    const [token] = useAuthStore(state => [state.token])
    const { data, isLoading, error } = useCustomQuery('get-workspaces', () => getWorkspaces(token || ""), token || "");

    if (isLoading) return <Loading />
    if (error) return <p>error</p>

    return (
        <div className="flex flex-col flex-1 px-6 py-4">
            <div className="flex justify-between items-center">
                <div>
                    <h3 className="text-xl font-semibold">Workspaces</h3>
                    <span className="font-light mt-4">Create a workspace from a <Link to="/templates" className="text-info">Template</Link></span>
                </div>
                <WorkspaceModal />
            </div>

            <div className="overflow-x-auto mt-12">
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
                                <tr key={index} className="hover">
                                    <td className="font-semibold cursor-pointer hover:text-info hover:underline"><Link to={`/workspaces/${workspace.name}`}>{workspace.name}</Link></td>
                                    <td>{workspace.template}</td>
                                    <td className="truncate">{workspace.description}</td>
                                    <td className="text-info underline"><a href={workspace.url} target="_blank" rel="noopener noreferrer">{workspace.url}</a></td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
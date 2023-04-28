import { apiConstants } from "../constants/api.constant"
import { IResponse } from "../models/response.model"
import { IWorkspace } from "../models/workspace.model"
import useAuthStore from "../store/user.store"

const token = useAuthStore(state => state.token)

export const getWorkspaces = async (): Promise<IWorkspace[]> => {
    const result = await (await fetch(`${apiConstants.API_URL}/workspaces/list`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })).json() as IResponse<IWorkspace[]>

    return result.data
}
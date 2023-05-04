import { apiConstants } from "../constants/api.constant"
import { IResponse } from "../models/response.model"
import { ICreateWorkspace, IWorkspace } from "../models/workspace.model"

export const getWorkspaces = async (token: string): Promise<IWorkspace[]> => {
    const result = await (await fetch(`${apiConstants.API_URL}/workspaces/list`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })).json() as IResponse<IWorkspace[]>

    return result.data
}

export const createWorkspace = async (data: ICreateWorkspace, token: string): Promise<IWorkspace> => {
    console.log('data', data)
    const result = await (await fetch(`${apiConstants.API_URL}/workspaces/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
    })).json() as IResponse<IWorkspace>

    return result.data
}
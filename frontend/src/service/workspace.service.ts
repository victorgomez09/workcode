import { apiConstants } from "../constants/api.constant"
import { IResponse } from "../models/response.model"

import { IWorkspace } from "../models/workspace.model"

export const getWorkspaces = async (token: string | null): Promise<IWorkspace[]> => {
    const result = await (await fetch(`${apiConstants.API_URL}/workspaces/find`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })).json() as IResponse<IWorkspace[]>;

    return result.data
}
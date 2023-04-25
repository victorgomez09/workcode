import { apiConstants } from "../constants/api.constant";
import { ILoggedIn, ILogin, IRegister } from "../models/auth.model";

export const login = async (data: ILogin): Promise<ILoggedIn> => {
    const result = await fetch(apiConstants.API_URL, {
        method: 'POST',
        body: JSON.stringify(data)
    })
    
    return await result.json();
}

export const register = async (data: IRegister): Promise<ILoggedIn> => {
    const result = await fetch(apiConstants.API_URL, {
        method: 'POST',
        body: JSON.stringify(data)
    })

    return await result.json();
}
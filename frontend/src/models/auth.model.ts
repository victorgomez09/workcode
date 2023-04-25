import { User } from "./user.model";

export interface ILogin {
    email: string;
    password: string;
}

export interface IRegister {
    email: string;
    name: string;
    password: string;
}

export interface ILoggedIn {
    token: string;
    refresh_token: string;
    user: User
}
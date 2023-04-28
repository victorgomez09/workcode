import { Link } from "react-router-dom"
import { User } from "../models/user.model"
import { getInitialsFromString } from "../utils/string.util"

interface INavbar {
    user: User
}

export const Navbar = ({ user }: INavbar) => {
    return (
        <div className="navbar bg-base-100">
            <div className="navbar-start">
                <div className="dropdown">
                    <label tabIndex={0} className="btn btn-ghost lg:hidden">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h8m-8 6h16" /></svg>
                    </label>
                    <ul tabIndex={0} className="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52">
                        <li><a href="/workspaces">Workspaces</a></li>
                        <li><Link to="/templates">Templates</Link></li>
                        <li><Link to="/users">Users</Link></li>
                    </ul>
                </div>
                <a className="btn btn-ghost normal-case text-xl">workcode</a>
            </div>
            <div className="navbar-start hidden lg:flex">
                <ul className="menu menu-horizontal px-1">
                    <li><a href="/workspaces">Workspaces</a></li>
                    <li><Link to="/templates">Templates</Link></li>
                    <li><Link to="/users">Users</Link></li>
                </ul>
            </div>
            <div className="navbar-end">
                <a className="btn">Get started</a><div className="avatar placeholder">
                    <div className="bg-neutral-focus text-neutral-content rounded-full w-8">
                        <span className="text-xs">{getInitialsFromString(user.name)}</span>
                    </div>
                </div>
            </div>
        </div>
    )
}
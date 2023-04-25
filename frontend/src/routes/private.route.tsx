import { Outlet, useNavigate } from "react-router-dom"
import useAuthStore from "../store/user.store"
import { Navbar } from "../components/navbar.component"

export const PrivateRoute = () => {
    const navigate = useNavigate()
    const { user, token } = useAuthStore((state) => ({user: state.user, token: state.token}))

    if (user && token) navigate("/login")

    return (
        <div className="flex flex-col flex-1">
            <Navbar user={user!} />

            <Outlet />
        </div>
    )
}
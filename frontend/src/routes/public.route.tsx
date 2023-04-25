import { Navigate, Outlet } from "react-router-dom"
import useAuthStore from "../store/user.store"

export const PublicRoute = () => {
    const { user, token } = useAuthStore((state) => ({ user: state.user, token: state.token }))

    console.log('user', user)
    console.log('token', token)

    if (user && token) return <Navigate to="/app" />

    return <Outlet />
}
import { Outlet, useNavigate } from "react-router-dom"
import useAuthStore from "../store/user.store"

export const PublicRoute = () => {
    const navigate = useNavigate()
    
    const { user, token } = useAuthStore((state) => ({user: state.user, token: state.token}))

    if (user && token) navigate('/app')

    return <Outlet />
}
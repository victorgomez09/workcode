import { Route } from 'react-router-dom'

import './App.css'

import { PublicRoute } from './routes/public.route'
import { PrivateRoute } from './routes/private.route'
import Login from './views/login.view'
import Register from './views/register.view'
import Dashboard from './views/dashboard.view'

function App() {

  return (
    <>
      <Route element={<PublicRoute/>}>
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
      </Route>

      <Route element={<PrivateRoute />}>
        <Dashboard />
      </Route>
    </>
  )
}

export default App

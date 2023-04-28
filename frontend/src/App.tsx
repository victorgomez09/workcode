import { Route, Routes } from 'react-router-dom'

import { PublicRoute } from './routes/public.route'
import { PrivateRoute } from './routes/private.route'
import Login from './views/login.view'
import Register from './views/register.view'
import Dashboard from './views/dashboard.view'
import { QueryClient, QueryClientProvider } from 'react-query'

function App() {
  const queryClient = new QueryClient({});

  return (
    <QueryClientProvider client={queryClient}>
      <Routes>
        <Route element={<PublicRoute />}>
          <Route path='/login' element={<Login />} />
          <Route path='/register' element={<Register />} />
        </Route>

        <Route element={<PrivateRoute />}>
          <Route path="/app" element={<Dashboard />} />
        </Route>
      </Routes>
    </QueryClientProvider>
  )
}

export default App

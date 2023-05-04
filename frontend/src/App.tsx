import { Route, Routes } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from 'react-query'

import { PublicRoute } from './routes/public.route'
import { PrivateRoute } from './routes/private.route'
import Login from './views/login.view'
import Register from './views/register.view'
import Workspaces from './views/workspaces/workspaces.view'
import WorkspaceDetails from './views/workspaces/workspace-details.view'

function App() {
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
      <Routes>
        <Route element={<PublicRoute />}>
          <Route path='/login' element={<Login />} />
          <Route path='/register' element={<Register />} />
        </Route>

        <Route element={<PrivateRoute />}>
          <Route path="/workspaces" element={<Workspaces />} />
          <Route path="/workspaces/:name" element={<WorkspaceDetails />} />
        </Route>
      </Routes>
    </QueryClientProvider>
  )
}

export default App

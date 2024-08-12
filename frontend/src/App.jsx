import './App.css'
import SignUp from './pages/SignUp'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import HomePage from './pages/HomePage'
import LoginPage from './pages/LoginPage'
import JobDetail from './components/job/JobDetail';

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/signup",
    element: <SignUp />,
  },
  {
    path: "/job/:id",
    element: <JobDetail />,
  },
]);

function App() {
  return (
    <RouterProvider router={router} />

  )
}

export default App

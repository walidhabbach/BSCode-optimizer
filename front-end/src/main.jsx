import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import { QueryClientProvider, QueryClient } from 'react-query'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import ErrorPage from "./pages/ErrorPage/ErrorPage.jsx";
import AuthPage from './pages/AuthPage/AuthPage.jsx';
 import RegisterPage from './pages/RegisterPage/RegisterPage.jsx';

const queryClient = new QueryClient()
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/login",
    element: <AuthPage />,
 
  },
  {
    path: "/register",
    element: <RegisterPage />, 
  }
]);
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
         <RouterProvider router={router} />

    </QueryClientProvider>
  </React.StrictMode>,
)

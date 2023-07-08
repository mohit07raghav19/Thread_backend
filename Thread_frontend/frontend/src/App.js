import logo from './logo.svg';
import './App.css';
import { HomePage } from './pages/homePage/HomePage';
import {
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Link
} from "react-router-dom"
import { UserProfile } from './pages/UserProfile/UserProfile';

const router = createBrowserRouter(createRoutesFromElements(
    <Route path="/">
      <Route index element={<HomePage />} />
      <Route path="/we" element={<UserProfile />} />
    </Route>
  // <Route path="/" element={<Layout />}>
  //   <Route path="*" element={<NotFound />} />
  // </Route>
))

function App() {
  return (
    <RouterProvider router={router} />
  )
}

export default App;

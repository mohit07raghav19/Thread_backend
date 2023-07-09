import logo from "./logo.svg";
import "./App.css";
import {
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Link,
} from "react-router-dom";
import Login from "./pages/loginPage/Login";
import { HomePage } from "./pages/homePage/HomePage";
import { UserProfile } from "./pages/UserProfile/UserProfile";
import Gallery from "./pages/Gallery/Gallery";
import Contactus from "./pages/Contactus/Contactus";
import Aboutus from "./pages/Aboutus/Aboutus";
import Signup from "./pages/loginPage/Signup";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route index element={<Login />} />
      <Route path="/signup" element={<Signup/>} />
      <Route path="/home" element={<HomePage />} />
      <Route path="/gallery" element={<Gallery />} />
      <Route path="/contactus" element={<Contactus />} />
      <Route path="/aboutus" element={<Aboutus />} />
      <Route path="/profile" element={<UserProfile />} />
    </Route>
  )
);

function App() {
  return <RouterProvider router={router} />;
}

export default App;

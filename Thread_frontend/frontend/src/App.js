import "./App.css";
import {
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  Link,
} from "react-router-dom";
import Login, { loader as loginLoader } from "./pages/loginPage/Login";
import { HomePage, loader as homeloader } from "./pages/homePage/HomePage";
import { UserProfile } from "./pages/UserProfile/UserProfile";
import Gallery, { loader as galleryloader } from "./pages/Gallery/Gallery";
import Contactus from "./pages/Contactus/Contactus";
import Aboutus from "./pages/Aboutus/Aboutus";
import Signup, { loader as signupLoader } from "./pages/loginPage/Signup";
import { NotFound } from "./components/NotFound";
import Userconnect, {
  loader as userconnectloader,
} from "./pages/Connect/Userconnect";
import "react-toastify/dist/ReactToastify.css";
import { Queries , loader as queryloader} from "./pages/UserProfile/Queries";
import { ToastContainer } from "react-toastify";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route index element={<Login />} loader={loginLoader} />
      <Route path="/signup" element={<Signup />} loader={signupLoader} />
      <Route path="/home" element={<HomePage />} loader={homeloader} />
      <Route path="/gallery" element={<Gallery />} loader={galleryloader} />
      <Route path="/contactus" element={<Contactus />} />
      <Route
        path="/connections"
        element={<Userconnect />}
        loader={userconnectloader}
      />
      <Route path="/queries" element={<Queries />} loader={queryloader} />
      <Route path="/aboutus" element={<Aboutus />} />
      <Route path="/profile" element={<UserProfile />} />
      <Route path="*" element={<NotFound />} />
    </Route>
  )
);

function App() {
  return (
    <>
      <ToastContainer />
      <RouterProvider router={router} />;
    </>
  );
}

export default App;

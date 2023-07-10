import React, { useEffect } from "react";
import Footer from "../../components/Footer";
import UserNavBar from "../../components/UserNavBar";
import { redirect, useNavigate } from "react-router-dom";
export function loader({ req }) {
  console.log(sessionStorage.getItem("jwtToken") == null);
  if (sessionStorage.getItem("jwtToken") == null) {
    throw redirect("/?message=PleaseLogin");
  }
  return null;
}
export default function Gallery() {
  const navigate = useNavigate();
  useEffect(() => {
    if (!sessionStorage.getItem("jwtToken")) {
      navigate("/?message=PleaseLogin");
    }
  }, []);
  return (
    <div>
      <UserNavBar />
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        <div className="grid gap-4">
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/HarshitP.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post0.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/demo.jpg"
              alt=""
            />
          </div>
        </div>
        <div className="grid gap-4">
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/15.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post1.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post2.jpeg"
              alt=""
            />
          </div>
        </div>
        <div className="grid gap-4">
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post3.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post4.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/post5.png"
              alt=""
            />
          </div>
        </div>
        <div className="grid gap-4">
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/user1.jpg"
              alt=""
            />
          </div>
          <div>
            <img
              className="h-auto max-w-full rounded-lg"
              src="MyImages/user2.jpg"
              alt=""
            />
          </div>
          {/* <div>
              <img
                className="h-auto max-w-full rounded-lg"
                src="MyImages/user3.jpg"
                alt=""
              />
            </div> */}
        </div>
      </div>
      <Footer />
    </div>
  );
}

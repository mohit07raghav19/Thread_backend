import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import { JwtDecoder } from "../../Utils/JwtDecoder";
import { Modal, Ripple, initTE } from "tw-elements";
import { useEffect } from "react";
const UserSideBar = ({ setPosts }) => {
  const Token = sessionStorage.getItem("jwtToken");
  const navigate = useNavigate();

  const decoded = JwtDecoder(Token);

  const navigation = [
    {
      href: "/gallery",
      name: "Gallery",
      icon: (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className="w-8 h-8"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width={1.5}
          stroke-linecap="round"
          stroke-linejoin="round"
        >
          <rect x="3" y="3" width="18" height="18" rx="2" />
          <circle cx="8.5" cy="8.5" r="1.5" />
          <path d="M20.4 14.5L16 10 4 20" />
        </svg>
      ),
    },
    {
      href: "/aboutus",
      name: "About Us",
      icon: (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          stroke="currentColor"
          stroke-width={1.5}
          stroke-linecap="round"
          stroke-linejoin="round"
          className="w-8 h-8"
          viewBox="0 0 50 50"
        >
          <path d="M 25 1 C 11.222656 1 0 10.878906 0 23.1875 C 0 29.234375 2.773438 34.664063 7.21875 38.6875 C 6.546875 40.761719 5.046875 42.398438 3.53125 43.65625 C 2.714844 44.332031 1.933594 44.910156 1.3125 45.46875 C 1.003906 45.746094 0.722656 46.027344 0.5 46.375 C 0.277344 46.722656 0.078125 47.21875 0.21875 47.75 L 0.34375 48.15625 L 0.6875 48.375 C 1.976563 49.117188 3.582031 49.246094 5.3125 49.125 C 7.042969 49.003906 8.929688 48.605469 10.78125 48.09375 C 14.375 47.101563 17.75 45.6875 19.53125 44.90625 C 21.289063 45.273438 23.054688 45.5 24.90625 45.5 C 38.683594 45.5 49.90625 35.621094 49.90625 23.3125 C 49.90625 11.007813 38.78125 1 25 1 Z M 25 3 C 37.820313 3 47.90625 12.214844 47.90625 23.3125 C 47.90625 34.402344 37.730469 43.5 24.90625 43.5 C 23.078125 43.5 21.355469 43.320313 19.625 42.9375 L 19.28125 42.84375 L 19 43 C 17.328125 43.738281 13.792969 45.179688 10.25 46.15625 C 8.476563 46.644531 6.710938 47.019531 5.1875 47.125 C 4.167969 47.195313 3.539063 46.953125 2.84375 46.78125 C 3.339844 46.355469 4.019531 45.847656 4.8125 45.1875 C 6.554688 43.742188 8.644531 41.730469 9.375 38.75 L 9.53125 38.125 L 9.03125 37.75 C 4.625 34.015625 2 28.875 2 23.1875 C 2 12.097656 12.175781 3 25 3 Z M 23.8125 12.8125 C 23.511719 12.8125 23.40625 12.988281 23.40625 13.1875 L 23.40625 15.8125 C 23.40625 16.113281 23.613281 16.1875 23.8125 16.1875 L 26.1875 16.1875 C 26.488281 16.1875 26.59375 16.011719 26.59375 15.8125 L 26.59375 13.1875 C 26.59375 12.886719 26.386719 12.8125 26.1875 12.8125 Z M 23.90625 20.09375 C 23.605469 20.09375 23.5 20.300781 23.5 20.5 L 23.5 33.90625 C 23.5 34.207031 23.707031 34.3125 23.90625 34.3125 L 23.90625 34.40625 L 26.1875 34.40625 C 26.488281 34.40625 26.59375 34.199219 26.59375 34 L 26.59375 20.5 C 26.59375 20.199219 26.386719 20.09375 26.1875 20.09375 Z" />
        </svg>
      ),
    },
    {
      href: "/connections",
      name: "Connections",
      icon: (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          strokeWidth={1.5}
          stroke="currentColor"
          className="w-8 h-8"
          viewBox="0 0 24 24"
          fill="none"
          stroke-linecap="round"
          stroke-linejoin="round"
        >
          <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
          <circle cx="9" cy="7" r="4"></circle>
          <path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
          <path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
        </svg>
      ),
    },
  ];

  const navsFooter = [
    {
      href: "/contactus",
      name: "Contact Us",
      icon: (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="w-8 h-8"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M9.879 7.519c1.171-1.025 3.071-1.025 4.242 0 1.172 1.025 1.172 2.687 0 3.712-.203.179-.43.326-.67.442-.745.361-1.45.999-1.45 1.827v.75M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-9 5.25h.008v.008H12v-.008z"
          />
        </svg>
      ),
    },
    {
      href: "/?message=LogoutSuccessFully",
      name: "Logout",
      icon: (
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="w-8 h-8"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15m3 0l3-3m0 0l-3-3m3 3H9"
          />
        </svg>
      ),
    },
  ];

  useEffect(() => {
    initTE({ Modal, Ripple });
  }, []);

  function handlepost(e) {
    let un = document.getElementById("un").value;
    let fn = document.getElementById("fn");
    const formData = new FormData();
    if(un=="" && fn.files.length==0){
        console.log("Please send data");
        e.target.setAttribute("data-te-modal-dismiss", " ");
        return;
    }
    if(fn.files[0]){
        formData.append("file", fn.files[0]);
    }
    else{
        formData.append("file",null);
    }
    formData.append("post", JSON.stringify({ description: un }));
    axios
      .post("http://localhost:8080/posts/", formData, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res)
        // e.target.setAttribute("data-te-modal-dismiss","");
        e.target.setAttribute("data-te-modal-dismiss", " ");
        console.log(e.target)
        axios
          .get(`http://localhost:8080/posts/user/${decoded.userName}`, {
            headers: {
              Authorization: `Bearer ${Token}`,
            },
          })
          .then((res) => {
            // console.log(res.data);
            window.location.reload();
            // setPosts(res.data.data);
          });

        // console.log(e.target)
      })
      .catch((e) => {
        console.log(e);
      });
  }
  return (
    <>
      <nav className="fixed top-0 left-0 w-full h-full border-r bg-white space-y-8 sm:w-80">
        <div class="flex flex-col h-full">
          <div className="h-20 flex pl-6 items-center pt-6">
            <div className="flex justify-center items-center">
              <img
                src="/MyImages/logo-final.png"
                width={170}
                height={160}
                className="mx-auto"
              />
              {/* change to thread logo */}
            </div>
          </div>
          <div className="flex-1 flex flex-col h-full overflow-auto">
            <ul className="px-4 pt-8 text-sm font-medium flex-1">
              {navigation.map((item, idx) => (
                <li key={idx}>
                  <Link
                    to={item.href}
                    className="flex items-center mt-4 gap-x-2 text-gray-600 p-2 rounded-lg  hover:bg-gray-50 active:bg-gray-100 duration-150"
                  >
                    <div className="text-gray-500">{item.icon}</div>
                    <p className="text-lg">{item.name}</p>
                  </Link>
                </li>
              ))}
              <li>
                <button
                  className="flex items-center mt-4 gap-x-2 text-gray-600 p-2 rounded-lg  hover:bg-gray-50 active:bg-gray-100 duration-150"
                  data-te-toggle="modal"
                  data-te-target="#exampleModalScrollable"
                  data-te-ripple-init=""
                  data-te-ripple-color="light"
                >
                  <div className="text-gray-500">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="32"
                      height="32"
                      viewBox="0 0 24 24"
                      fill="none"
                      stroke="currentColor"
                      stroke-width="1.5"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                    >
                      <line x1="12" y1="5" x2="12" y2="19"></line>
                      <line x1="5" y1="12" x2="19" y2="12"></line>
                    </svg>
                  </div>
                  <p className="text-lg">Add Post</p>
                </button>
              </li>
            </ul>

            <div>
              <ul className="px-4 pb-4 text-sm font-medium">
                {navsFooter.map((item, idx) => (
                  <li key={idx}>
                    <Link
                      to={item.href}
                      className="flex items-center mt-4 gap-x-2 text-gray-600 p-2 rounded-lg  hover:bg-gray-50 active:bg-gray-100 duration-150"
                    >
                      <div className="text-gray-500">{item.icon}</div>
                      <p className="text-lg">{item.name}</p>
                    </Link>
                  </li>
                ))}
              </ul>
              <div className="py-4 px-4 border-t">
                <div className="flex items-center gap-x-4">
                  <img
                    src={"/MyImages/" + decoded.userProfileImage}
                    className="w-12 h-12 rounded-full"
                  />
                  <div>
                    <span className="block text-gray-700 text-sm font-semibold">
                      {decoded.userName}
                    </span>
                    <Link
                      to="/home"
                      className="block mt-px text-gray-600 hover:text-indigo-600 text-xs"
                    >
                      Go Back to Home
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
      <div
        data-te-modal-init=""
        className="fixed left-0 top-0 z-[1055] hidden h-full w-full overflow-y-auto overflow-x-hidden outline-none"
        id="exampleModalScrollable"
        tabIndex={-1}
        aria-labelledby="exampleModalScrollableLabel"
        aria-hidden="true"
      >
        <div
          data-te-modal-dialog-ref=""
          className=" relative h-[calc(100%-1rem)] z-20 w-auto translate-y-[-50px] opacity-0 transition-all duration-300 ease-in-out min-[576px]:mx-auto min-[576px]:mt-7 min-[576px]:h-[calc(100%-3.5rem)] min-[576px]:max-w-[500px]"
        >
          <div className="pointer-events-auto z-10 relative flex max-h-[100%] w-full flex-col overflow-hidden rounded-md border-none bg-white bg-clip-padding text-current shadow-lg outline-none dark:bg-neutral-600">
            <div className="flex flex-shrink-0 items-center justify-between rounded-t-md border-b-2 border-neutral-100 border-opacity-100 p-4 dark:border-opacity-50">
              {/*Modal title*/}
              <h5
                className="text-xl font-medium leading-normal text-neutral-800 dark:text-neutral-200"
                id="exampleModalScrollableLabel"
              >
                Add New Post
              </h5>
              {/*Close button*/}
              <button
                type="button"
                className="box-content rounded-none border-none hover:no-underline hover:opacity-75 focus:opacity-100 focus:shadow-none focus:outline-none"
                data-te-modal-dismiss=""
                aria-label="Close"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  className="h-6 w-6"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </button>
            </div>
            {/*Modal body*/}
            <div className="relative overflow-y-auto p-4">
              <form
                onSubmit={(e) => e.preventDefault()}
                className="space-y-5 m-8"
              >
                <div>
                  <label className="font-medium">Description</label>
                  <input
                    id="un"
                    type="text"
                    required
                    pattern="^[A-Za-z\s]+$"
                    name="UserFullName"
                    className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                  />
                </div>
                <div>
                  <label className="font-medium">Profile Image</label>
                  <input
                    id="fn"
                    type="file"
                    required
                    name="profileImage"
                    accept="image/jpeg, image/png, image/gif,image/jpg,image/bmp"
                    className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                  />
                </div>

                <button></button>
              </form>
            </div>
            {/*Modal footer*/}
            <div className="flex flex-shrink-0 flex-wrap items-center justify-end rounded-b-md border-t-2 border-neutral-100 border-opacity-100 p-4 dark:border-opacity-50">
              <button
                type="button"
                className="inline-block rounded bg-primary-100 px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-primary-700 transition duration-150 ease-in-out hover:bg-primary-accent-100 focus:bg-primary-accent-100 focus:outline-none focus:ring-0 active:bg-primary-accent-200"
                data-te-modal-dismiss=""
                data-te-ripple-init=""
                data-te-ripple-color="light"
              >
                Close
              </button>
              <button
                onDoubleClick={(e) => handlepost(e)}
                type="button"
                className="ml-1 inline-block rounded bg-primary px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]"
              >
                Update
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default UserSideBar;

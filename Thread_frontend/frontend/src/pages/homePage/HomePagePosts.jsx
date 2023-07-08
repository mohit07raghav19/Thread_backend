import { Link } from "react-router-dom";
import react, { useEffect, useRef, useState } from "react";
import axios from "axios";
import { JwtDecoder } from "../../Utils/JwtDecoder";

export const HomePagePosts = () => {
  const [posts, setPosts] = useState([]);
  const Token = sessionStorage.getItem("jwtToken");
  const decoded = JwtDecoder(Token);
  //   let userid=
  function deleteComment(e, cid) {
    axios
      .delete(`http://localhost:8080/comment/${cid}`, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
    axios
      .get("http://localhost:8080/posts/feed/", {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setPosts(res.data.data);
      });
  }
  //   useEffect(()=>{},[])
  function addComment(e, comm) {
    const data = { commentText: e.target[0].value };
    axios
      .post(`http://localhost:8080/post/${comm}/comment`, data, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res.data)
        let newar = posts.map((item) => {
          if (item.postId == comm) {
            // let data=res.data;
            return { ...item, comments: res.data.data };
          } else {
            return item;
          }
        });
        setPosts(newar);
        e.target[0].value = "";
      })
      .catch((w) => {
        console.log(w);
      });
  }
  useEffect(() => {
    axios
      .get("http://localhost:8080/posts/feed/", {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        console.log(res.data);
        setPosts(res.data.data);
      });
  }, []);

  return (
    <section className="relative ">
      <section className="absolute overflow-y-scroll h-screen noscrollbar left-80 py-6 w-3/5 min-h-screen .overflow-auto .overscroll-auto">
        <div className="max-w-screen-xl mx-auto px-4 md:px-8 ">
          <div className="relative">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="absolute top-0 bottom-0 w-6 h-6 my-auto text-gray-400 left-3"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
              />
            </svg>
            <input
              type="text"
              placeholder="Search"
              className="w-full py-3 pl-12 pr-4 text-gray-500 border rounded-md outline-none bg-gray-50 focus:bg-white focus:border-indigo-600"
            />
          </div>

          <div className="mt-12 flex justify-center ">
            <ul className="grid gap-8 sm:grid-cols-1 w-3/5  md:grid-cols-1">
              {posts.map((item, idx) => (
                <li key={idx} className="border-2 p-4 shadow-md rounded-md">
                  <div className="py-4 px-4">
                    <div className="flex items-center gap-x-4">
                      <img
                        src={"/MyImages/" + item.profileImage}
                        className="w-12 h-12 rounded-full"
                      />
                      <div>
                        <span className="block text-gray-700 text-sm font-semibold">
                          {item.userFullName}
                        </span>
                        <p className="block mt-px text-gray-600 hover:text-indigo-600 text-xs">
                          {item.userName}
                        </p>
                      </div>
                    </div>
                  </div>
                  {item.postImage && (
                    <div className="w-full h-60 sm:h-52 md:h-56">
                      <img
                        src={"/MyImages/" + item.postImage}
                        className="w-full h-full object-cover object-center shadow-md rounded-xl"
                        alt=""
                      />
                    </div>
                  )}
                  <div className="mt-4">
                    <p className="text-gray-600 mt-2">{item.description}</p>
                    <div className="mt-3 flex gap-4 text-gray-400">
                      <button>
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                          fill="none"
                          stroke="currentColor"
                          stroke-width="1.5"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        >
                          <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
                        </svg>
                      </button>
                    </div>
                  </div>

                  {item.comments.length > 0 && (
                    <>
                      <span className="block text-gray-700 text-sm font-semibold mt-3">
                        Comments -
                      </span>
                      <ol className="h-24 overflow-y-scroll ">
                        {item.comments.map((ite, id) => {
                          return (
                            <li key={id}>
                              <div className="py-4 px-4 border mt-1">
                                <div className="flex items-between gap-x-4">
                                  <img
                                    src={"/MyImages/" + ite.profileImage}
                                    className="w-6 h-6 rounded-full"
                                  />
                                  <div className="flex-col items-start gap-x-4">
                                    <div className="flex gap-x-4 justify-between">
                                      <span className="block text-gray-700 text-sm font-semibold">
                                        {ite.userFullName}
                                      </span>
                                      <p className="block mt-px text-gray-600  text-xs">
                                        {ite.creationTime}
                                      </p>
                                    </div>
                                    <p className="block mt-px text-gray-600  text-xs">
                                      {ite.commentText}
                                    </p>
                                  </div>
                                  {decoded.sub == ite.userName && (
                                    <div
                                      className="ml-auto"
                                      onClick={(e) =>
                                        deleteComment(e, ite.commentId)
                                      }
                                    >
                                      <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="24"
                                        height="24"
                                        viewBox="0 0 24 24"
                                        fill="none"
                                        stroke="currentColor"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                      >
                                        <polyline points="3 6 5 6 21 6"></polyline>
                                        <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                                        <line
                                          x1="10"
                                          y1="11"
                                          x2="10"
                                          y2="17"
                                        ></line>
                                        <line
                                          x1="14"
                                          y1="11"
                                          x2="14"
                                          y2="17"
                                        ></line>
                                      </svg>
                                    </div>
                                  )}
                                </div>
                              </div>
                            </li>
                          );
                        })}
                      </ol>
                    </>
                  )}
                  <form
                    onSubmit={(e) => {
                      e.preventDefault();
                      addComment(e, item.postId);
                    }}
                    className="mt-5 items-center justify-center sm:flex"
                  >
                    <textarea
                      rows={1}
                      placeholder="Comment"
                      className="text-gray-500 w-full p-3 rounded-md border outline-none focus:border-indigo-600"
                    ></textarea>
                    <button className="w-full mt-3 px-5 py-3 rounded-md text-white hover:bg-gray-200 active:bg-black-700 duration-150 outline-none shadow-md focus:shadow-none  sm:mt-0 sm:ml-3 sm:w-auto">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="24"
                        height="24"
                        viewBox="0 0 512 512"
                      >
                        <path d="M16,464,496,256,16,48V208l320,48L16,304Z" />
                      </svg>
                    </button>
                  </form>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </section>
    </section>
  );
};
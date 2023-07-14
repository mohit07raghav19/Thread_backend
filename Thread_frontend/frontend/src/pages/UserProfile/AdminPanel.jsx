import axios from "axios";
import React, { useEffect, useState } from "react";
import UserSideBar from "./UserSideBar";
import { JwtDecoder } from "../../Utils/JwtDecoder";
import { UserPosts } from "./UserPosts";

export const AdminPanel = () => {
  const [postStats, setPostStats] = useState(0);
  const Token = sessionStorage.getItem("jwtToken");
  const url = "http://localhost:8080/all/posts/";
  const [userStats, setUserStats] = useState(0);
  const [posts, setPosts] = useState([]);
  const [matchArray, setMatchArray] = useState(posts);
  const decoded = JwtDecoder(Token);
  useEffect(() => {
    axios
      .get(`${url}`, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res.data);
        setPosts(res.data.data);
        setMatchArray(res.data.data);
      });
  }, []);
  useEffect(() => {}, [posts]);
  useEffect(() => {
    axios
      .get(`${url}`, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res);
        setPostStats(res.data.count);
        setUserStats(res.data.count);
      })
      .catch((e) => {
        console.log(e);
      });
    // axios
    //   .get(`http://localhost:8080/getallappusers`, {
    //     headers: {
    //       Authorization: `Bearer ${Token}`,
    //     },
    //   })
    //   .then((res) => {
    //     console.log(res);
    //     // setPostStats(res.data.count)
    //   })
    //   .catch((e) => {
    //     console.log(e);
    //   });
  }, []);
  return (
    <>
      <UserSideBar />
      <section className="py-14">
        <div className="max-w-screen-xl mx-auto px-4 text-gray-600 md:px-8">
          <div className="max-w-2xl mx-auto text-center">
            <h3 className="text-gray-800 text-3xl font-semibold sm:text-4xl">
              Our customers are always happy
            </h3>
          </div>
          <div className="mt-12">
            <ul className="flex flex-col items-center justify-center gap-y-10 sm:flex-row sm:flex-wrap lg:divide-x">
              <li className="text-center px-12 md:px-16">
                <h4 className="text-4xl text-indigo-600 font-semibold">
                  {userStats}+
                </h4>
                <p className="mt-3 font-medium">Users</p>
              </li>
              <li className="text-center px-12 md:px-16">
                <h4 className="text-4xl text-indigo-600 font-semibold">
                  {postStats}+
                </h4>
                <p className="mt-3 font-medium">Posts</p>
              </li>
            </ul>
          </div>
        </div>
      </section>
      <UserPosts
        posts={posts}
        setPosts={setPosts}
        matchArray={matchArray}
        setMatchArray={setMatchArray}
        url={url}
      />
    </>
  );
};

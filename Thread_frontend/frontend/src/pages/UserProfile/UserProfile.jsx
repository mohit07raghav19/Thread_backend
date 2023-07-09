import React, { useEffect, useState } from 'react'
import UserSideBar from './UserSideBar'
import { UserPosts } from './UserPosts'
import { redirect } from 'react-router-dom'
import axios from 'axios'
import { JwtDecoder } from '../../Utils/JwtDecoder'

export function loader({req}){
  console.log(sessionStorage.getItem("jwtToken")==null)
  if(sessionStorage.getItem("jwtToken")==null){
    throw redirect("/?message=PleaseLogin")
  }
  return null;
}
export const UserProfile = () => {
  const [posts, setPosts] = useState([]);
  const [matchArray, setMatchArray] = useState(posts);
  const Token = sessionStorage.getItem("jwtToken");
  const decoded = JwtDecoder(Token);
  useEffect(()=>{
    axios
      .get(`http://localhost:8080/posts/user/${decoded.userName}`, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res.data);
        setPosts(res.data.data);
        setMatchArray(res.data.data);
      });
  },[])
  useEffect(()=>{},[posts]);
  return (
    <>
    <UserSideBar setPosts={setPosts}  /> \
    <UserPosts posts={posts} setPosts={setPosts}  matchArray={matchArray} setMatchArray={setMatchArray}/>
    </>
  )
}

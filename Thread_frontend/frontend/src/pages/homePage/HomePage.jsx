import React, { useEffect, useState } from "react";
import HomepageSidebar from "./HomePageSidebar";
import { HomePagePosts } from "./HomePagePosts";
import { HomePageConnections } from "./HomePageConnections";
import axios from "axios";
export const HomePage = () => {
  const [nonConnections, setNonConnections] = useState([]);
  
  const Token = sessionStorage.getItem("jwtToken");
  useEffect(() => {
    axios
      .get("http://localhost:8080/nonconnections", {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        // console.log(res);
        setNonConnections(res.data.data);
      });
  }, []);
  return (
    <>
      <HomepageSidebar />
      <HomePagePosts nonConnections={nonConnections}/>
    <HomePageConnections nonConnections={nonConnections} setNonConnections={setNonConnections} Token={Token}/>
    </>
  );
};

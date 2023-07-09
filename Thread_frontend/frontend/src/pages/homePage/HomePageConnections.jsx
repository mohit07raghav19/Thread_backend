import axios from "axios";
import React, { useState, useEffect } from "react";
import "./HomePage.css"

const connections = ["E", "we", "e"];
export const HomePageConnections = ({nonConnections,setNonConnections,Token}) => {
  
  function handleconnections(e, name) {
    let data = {};
    axios.post(`http://localhost:8080/follow/user/${name}`, data, {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    })
    .then((res)=>{
      // console.log(e)
      e.target.innerText="Connected";
      e.target.style.backgroundColor="black";
      e.target.style.color="white";
      
      setTimeout(() => {
        axios
        .get("http://localhost:8080/nonconnections", {
          headers: {
            Authorization: `Bearer ${Token}`,
          },
        })
        .then((res) => {
          // console.log(res);
          setNonConnections(res.data.data);
          e.target.style.backgroundColor="white";
          e.target.style.color="black";
          e.target.innerText="Connect";
        });
      }, 2000);
    })
    ;
  }
  let non5 = nonConnections.filter((val, id) => {
    return id < 6;
  });
  return (
    <section className="relative">
      <section className="absolute right-6 pt-8 w-1/5 min-h-screen .overflow-x-hidden">
        <span className="block pb-6 text-black-200 text-2xl  font-semibold mb-4">
          New Connections
        </span>
        {nonConnections.length>0 &&<ul className="grid gap-2 sm:grid-cols-1 md:grid-cols-1">
          {non5.map((item, id) => {
            return (
              <li key={id}>
                <div className="py-4 px-4 border-2 rounded-xl opac-controller">
                  <div className="flex items-center justify-between gap-x-4">
                    <div className="flex gap-x-2 items-center">
                      <img
                        src={"/MyImages/" + item.profileImage}
                        className="w-10 h-10 rounded-full"
                      />
                      <div>
                        <span className="block text-gray-700 text-base font-semibold">
                          {item.userFullName}
                        </span>
                      </div>
                    </div>
                    <button
                      className="block mt-px text-black border-2 border-black rounded-md px-4 py-2 text-sm"
                      onClick={(e) => {
                        handleconnections(e, item.userName);
                      }}
                    >
                      Connect
                    </button>
                  </div>
                </div>
              </li>
            );
          })}
        </ul>}
        {nonConnections.length==0 && <span className="block text-gray-700 text-base font-semibold">
                          No Users left for connections
                        </span>}
      </section>
    </section>
  );
};

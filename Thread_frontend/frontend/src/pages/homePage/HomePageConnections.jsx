import axios from "axios";
import React,{useState,useEffect} from "react";

const connections = ["E", "we", "e"];
export const HomePageConnections = () => {
    const [nonConnections,setNonConnections]=useState([]);
    const Token=sessionStorage.getItem("jwtToken");
    useEffect(()=>{
        axios.get("http://localhost:8080/nonconnections",{headers:{
            Authorization:`Bearer ${Token}`
        }})
        .then((res)=>{
            console.log(res)
            setNonConnections(res.data.data)
        })
    },[])
    let non5=nonConnections.filter((val,id)=>{return id<6});
  return (
    <section className="relative">
      <section className="absolute right-6 py-6 w-1/5 min-h-screen .overflow-auto .overscroll-auto">
        <span className="block text-black-200 text-2xl  font-semibold mb-4">
          New Connections
        </span>
        <ul className="grid gap-2 sm:grid-cols-1 md:grid-cols-1">
          {non5.map((item, id) => {
            return (
              <li key={id}>
                <div className="py-4 px-4 border">
                  <div className="flex items-center justify-between gap-x-4">
                    <div className="flex gap-x-2 items-center">
                      <img
                        src={"/MyImages/"+item.profileImage}
                        className="w-10 h-10 rounded-full"
                      />
                      <div>
                        <span className="block text-gray-700 text-base font-semibold">
                          {item.userFullName}
                        </span>
                      </div>
                    </div>
                    <button className="block mt-px text-black border-2 border-black rounded-md px-4 py-2 text-sm">
                      Connect
                    </button>
                  </div>
                </div>
              </li>
            );
          })}
        </ul>
      </section>
    </section>
  );
};

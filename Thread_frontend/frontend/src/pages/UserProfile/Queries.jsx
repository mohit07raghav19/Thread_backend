import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { redirect, useNavigate } from 'react-router-dom';
import { JwtDecoder } from '../../Utils/JwtDecoder';
import { toast } from 'react-toastify';
export function loader({req}){
    if(sessionStorage.getItem("jwtToken")==null){
      throw redirect("/?message=PleaseLogin")
    }
  const Token = sessionStorage.getItem("jwtToken");
  let decoded=JwtDecoder(Token);
  if((!decoded.roles.includes('Admin'))){
    toast.error("You are not an Admin")
    throw redirect("/home");
  }
    
  return null;
  }
export const Queries = () => {
    const navigate=useNavigate()
    const [queries,setQueries]=useState([]);
    const Token = sessionStorage.getItem("jwtToken");
    useEffect(()=>{
        axios.get("http://localhost:8080/contactus/unanswered",{
            headers: {
              Authorization: `Bearer ${Token}`,
            },
          })
          .then((res)=>{
            console.log(res)
            setQueries(res.data.data);
          })
          .catch((e)=>{
            console.log(e)
          })
    },[])

    return (
        <section className="mt-12 max-w-screen-lg mx-auto px-4 md:px-8">
            <div>
                <h1 className="text-gray-800 text-3xl font-semibold">
                    Unanswered Queries
                </h1>
            </div>

            <ul className="mt-12 space-y-6">
                {
                    queries.map((item, idx) => (
                        <li key={idx} className="bg-white border-2 p-4 shadow-md rounded-md">
                            
                                <div>
                                    <div className="justify-between sm:flex">
                                        <div className="flex-1">
                                            <h2 className="text-xl text-md text-Black-700 font-bold">
                                                {item.name}
                                            </h2>
                                            <h3 className="text-xl font-sm text-cyan-600">
                                                {item.subject}
                                            </h3>
                                            <p className="text-gray-500 mt-2 pr-2">
                                                {item.message}
                                            </p>
                                        </div>
                                        <div className="mt-5 space-y-4 text-sm sm:mt-0 sm:space-y-2">
                                            <a href={"mailto:"+item.email} className="flex items-center text-gray-500">
                                                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 mr-2" viewBox="0 0 24 24" fill="currentColor" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><path d="M10 16l-6-6 6-6"/><path d="M20 21v-7a4 4 0 0 0-4-4H5"/></svg>
                                                <span className='pr-1'>Reply </span>
                                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"><path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"></path><polyline points="22,6 12,13 2,6"></polyline></svg>
                                            </a> 
                                        </div>
                                    </div>
                                </div>
                        </li>
                    ))
                }
            </ul>
            <button onClick={()=>{navigate("/profile")}} className="ml-1 mt-6 inline-block rounded bg-primary px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]">
                Back to Profile
            </button>
        </section>
    )
}
    
    

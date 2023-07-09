import axios from "axios";
import  { useEffect, useState } from "react";
import "./UserConnect.css"

function Userconnect(){
  const [connection, setConnection] = useState([]);

  const Token = sessionStorage.getItem("jwtToken");
  useEffect(() => {
    axios
      .get("http://localhost:8080/connections", {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      })
      .then((res) => {
        setConnection(res.data.data);
      });
  }, []);
  function handledisconnections(e, name) {
    axios.delete(`http://localhost:8080/unfollow/user/${name}`, {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    })
    .then((res)=>{
      e.target.innerText="Disconnected";
      e.target.style.backgroundColor="black";
      e.target.style.color="white";
      
      setTimeout(() => {
        axios
        .get("http://localhost:8080/connections", {
          headers: {
            Authorization: `Bearer ${Token}`,
          },
        })
        .then((res) => {
          setConnection(res.data.data);
          e.target.style.backgroundColor="white";
          e.target.style.color="black";
          e.target.innerText="Disconnect";
        });
      }, 2000);
    })
    ;
  }

  return (
    <div className="max-w-screen-xl mx-auto px-4 md:px-8">
      <div className="max-w-lg">
        <h3 className="text-gray-800 pt-10 text-xl font-bold sm:text-2xl">
          Your Connections
        </h3>
      </div>
      <div>
        {connection.length==0 ? <span className="block mt-12 pb-6 text-gray-500 text-xl  font-semibold mb-4">
        No Connections to Show. Please Add New Connections
        </span>:""}
      {
        connection.length>0 &&
        <table className="w-full table-auto text-sm text-left">
          <thead className="bg-gray-50 text-gray-600 font-medium border-b">
            <tr>
              <th className="py-3 px-6">User ID</th>
              <th className="py-3 px-6">User Name</th>
              <th className="py-3 px-6">Disconnect</th>
            </tr>
          </thead>
          <tbody className="text-gray-600 divide-y">
            {connection.length>0 &&connection.map((item, idx) => (
              <tr key={idx} className="opac-control">
                <td className="flex items-center gap-x-3 py-3 px-6 whitespace-nowrap">
                  <img
                    src={"MyImages/"+item.profileImage}
                    className="w-10 h-10 rounded-full"
                  />
                  <span className="block text-gray-700 text-xs">
                    {item.userName}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <span className="block text-gray-700 text-xs">
                    {item.userFullName}
                  </span>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                <button
                      className="block mt-px text-black border-2 border-black rounded-md px-4 py-2 text-sm"
                      onClick={(e) => {
                        handledisconnections(e, item.userName);
                      }}
                    >
                      Disconnect
                    </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>}
      </div>
    </div>
  );
};

export default Userconnect;
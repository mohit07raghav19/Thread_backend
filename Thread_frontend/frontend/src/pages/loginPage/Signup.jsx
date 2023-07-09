import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

export default function Signup() {
    // has number
const hasNumber = (number) => new RegExp(/[0-9]/).test(number);

// has mix of small and capitals
const hasSmall = (number) => new RegExp(/[a-z]/).test(number) ;
const hasCapital = (number) =>  new RegExp(/[A-Z]/).test(number);

// has special chars
const hasSpecial = (number) => new RegExp(/[!#@$%^&*)(+=._-]/).test(number);

const [length,setLength]=useState(0);

useEffect(() => {
    const passwordInput = document.getElementById("password");
    const updateLength = () => {
      setLength(passwordInput.value.length);
    };
  
    passwordInput.addEventListener("input", updateLength);
  
    return () => {
      passwordInput.removeEventListener("input", updateLength);
    };
  }, []);

// set color based on password strength
const strengthColor = (count) => {
  if (count < 2) return { label: 'Poor', color: 'Red' };
  if (count < 3) return { label: 'Weak', color: 'yellow' };
  if (count < 4) return { label: 'Normal', color: 'dark-yellow' };
  if (count < 5) return { label: 'Good', color: 'Green' };
  if (count < 6) return { label: 'Strong', color: 'DarkGreen' };
  return { label: 'Poor', color: 'Red' };
};
function passwordStrength(){
    let password = document.getElementById("password").value;
    if(length>0){
        let colorDiv = document.getElementById("passwordStrengthColor");
        let Strength = document.getElementById("passwordStrengthValue");
        let count=strengthIndicator(password);
        let obj = strengthColor(count);
        colorDiv.style.backgroundColor=obj.color;
        Strength.innerHTML=`${obj.label}`;
    }
}

// password strength indicator
 const strengthIndicator = (number) => {
  let strengths = 0;
  if (number.length > 7) strengths += 1;
  if (hasNumber(number)) strengths += 1;
  if (hasSpecial(number)) strengths += 1;
  if (hasCapital(number)) strengths += 1;
  if (hasSmall(number)) strengths += 1;
  if (number.length < 8) strengths = 1;
  
  return strengths;
};
  return (
    <main className="w-full flex">
            <div className="relative flex-1 hidden items-center justify-center h-screen bg-gray-900 lg:flex">
                <div className="relative z-10 w-full max-w-md">
                    <img src="https://floatui.com/logo-dark.svg" width={150} />
                    <div className=" mt-16 space-y-3">
                        <h3 className="text-white text-3xl font-bold">Give people the power to build community</h3>
                        <p className="text-gray-300">
                        We're commited to fostering a safe and supportive community for everyone We're commited to fostering a safe and supportive community for everyone
                        </p>
                        <div className="flex items-center -space-x-2 overflow-hidden">
                            <img src="https://randomuser.me/api/portraits/women/79.jpg" className="w-10 h-10 rounded-full border-2 border-white" />
                            <img src="https://api.uifaces.co/our-content/donated/xZ4wg2Xj.jpg" className="w-10 h-10 rounded-full border-2 border-white" />
                            <img src="https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=200&w=200&s=a72ca28288878f8404a795f39642a46f" className="w-10 h-10 rounded-full border-2 border-white" />
                            <img src="https://randomuser.me/api/portraits/men/86.jpg" className="w-10 h-10 rounded-full border-2 border-white" />
                            <img src="https://images.unsplash.com/photo-1510227272981-87123e259b17?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=200&w=200&s=3759e09a5b9fbe53088b23c615b6312e" className="w-10 h-10 rounded-full border-2 border-white" />
                            <p className="text-sm text-gray-400 font-medium translate-x-5">
                                Join 5.000+ users
                            </p>
                        </div>
                    </div>
                </div>
                <div
                    className="absolute inset-0 my-auto h-[500px]"
                    style={{
                        background: "linear-gradient(152.92deg, rgba(192, 132, 252, 0.2) 4.54%, rgba(232, 121, 249, 0.26) 34.2%, rgba(192, 132, 252, 0.1) 77.55%)", filter: "blur(118px)"
                    }}
                >

                </div>
            </div>
            <div className="flex-1 flex items-center justify-center h-screen">
                <div className="w-full max-w-md space-y-8 px-4 bg-white text-gray-600 sm:px-0">
                    <div className="">
                        <img src="https://floatui.com/logo.svg" width={150} className="lg:hidden" />
                        <div className="mt-5 space-y-2">
                            <h3 className="text-gray-800 text-2xl font-bold sm:text-3xl">Sign up</h3>
                        </div>
                    </div>
                    <form
                        onSubmit={(e) => e.preventDefault()}
                        className="space-y-5"
                    >
                        <div>
                            <label className="font-medium">
                                User Id
                            </label>
                            <input
                                type="text"
                                required
                                name="UserName"
                                className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                            />
                        </div>
                        <div>
                            <label className="font-medium">
                                Full name
                            </label>
                            <input
                                type="text"
                                required
                                name="UserFullName"
                                className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                            />
                        </div>
                        <div>
                            <label className="font-medium">
                                Email
                            </label>
                            <input
                                type="email"
                                required
                                name="Email"
                                className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                            />
                        </div>
                        <div>
                            <label className="font-medium">
                                Password
                            </label>
                            <input
                                type="password"
                                required
                                id='password'
                                name="Password"
                                pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*[\s\t]).{8,20}$"
                                onChange={passwordStrength}
                                className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border focus:border-indigo-600 shadow-sm rounded-lg"
                            />
                            {length>0 && <div className='flex items-center gap-3 m-1'>
                                <div className='font-small' id='passwordStrengthValue'></div>
                                <div className='h-2 w-20 rounded-lg' id='passwordStrengthColor'></div>
                            </div>}
                        </div>
                        <button
                            className="w-full px-4 py-2 text-white font-medium bg-indigo-600 hover:bg-indigo-500 active:bg-indigo-600 rounded-lg duration-150"
                        >
                            Create account
                        </button>
                        <p className="">Already have an account? <Link className="font-medium text-indigo-600 hover:text-indigo-500" to="/">Log in</Link></p>
                        
                    </form>
                </div>
            </div>
        </main>
  )
}

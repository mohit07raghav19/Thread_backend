import React, { useEffect, useState } from "react"
import { Link } from "react-router-dom";
// Initialization for ES Users
import {
    Modal,
    Ripple,
    initTE,
} from "tw-elements";

export default function ForgetPassword() {
    useEffect(() => {
        initTE({ Modal, Ripple });
    }, []);
    // has number
    const hasNumber = (number) => new RegExp(/[0-9]/).test(number);

    // has mix of small and capitals
    const hasSmall = (number) => new RegExp(/[a-z]/).test(number);
    const hasCapital = (number) => new RegExp(/[A-Z]/).test(number);

    // has special chars
    const hasSpecial = (number) => new RegExp(/[!#@$%^&*)(+=._-]/).test(number);

    const [length, setLength] = useState(0);

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
    function passwordStrength() {
        let password = document.getElementById("password").value;
        if (length > 0) {
            let colorDiv = document.getElementById("passwordStrengthColor");
            let Strength = document.getElementById("passwordStrengthValue");
            let count = strengthIndicator(password);
            let obj = strengthColor(count);
            colorDiv.style.backgroundColor = obj.color;
            Strength.innerHTML = `${obj.label}`;
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

    function ConfirmPassword() {
        if (document.getElementById('password').value !== (document.getElementById('confirm_password').value) || document.getElementById('password').value ==="") {
            document.getElementById('confirm_password').classList.add('focus:border-red-600')
            document.getElementById('confirm_password').classList.remove('focus:border-green-600')
        } else {
            document.getElementById('confirm_password').classList.add('focus:border-green-600')
            document.getElementById('confirm_password').classList.remove('focus:border-red-600')
        }
    }
    return (
        <>
            {/* Button trigger modal */}
            <button
                type="button"
                className="text-indigo-700"
                data-te-toggle="modal"
                data-te-target="#exampleModalScrollable"
                data-te-ripple-init=""
                data-te-ripple-color="light"
            >
                Forgot Password ?
            </button>
            {/* Modal */}
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
                    className="pointer-events-none relative h-[calc(100%-1rem)] w-auto translate-y-[-50px] opacity-0 transition-all duration-300 ease-in-out min-[576px]:mx-auto min-[576px]:mt-7 min-[576px]:h-[calc(100%-3.5rem)] min-[576px]:max-w-[500px]"
                >
                    <div className="pointer-events-auto relative flex max-h-[100%] w-full flex-col overflow-hidden rounded-md border-none bg-white bg-clip-padding text-current shadow-lg outline-none dark:bg-neutral-600">
                        <div className="flex flex-shrink-0 items-center justify-between rounded-t-md border-b-2 border-neutral-100 border-opacity-100 p-4 dark:border-opacity-50">
                            {/*Modal title*/}
                            <h5
                                className="text-xl font-medium leading-normal text-neutral-800 dark:text-neutral-200"
                                id="exampleModalScrollableLabel"
                            >
                                Change Password
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
                                    <label className="font-medium">
                                        Email
                                    </label>
                                    <input
                                        type="email"
                                        required
                                        name="email"
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
                                    {length > 0 && <div className='flex items-center gap-3 m-1'>
                                        <div className='font-small' id='passwordStrengthValue'></div>
                                        <div className='h-2 w-20 rounded-lg' id='passwordStrengthColor'></div>
                                    </div>}
                                </div>
                                <div>
                                    <label className="font-medium">
                                        Confirm Password
                                    </label>
                                    <input
                                        type="password"
                                        required
                                        id='confirm_password'
                                        name="Password"
                                        pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?!.*[\s\t]).{8,20}$"
                                        onChange={ConfirmPassword}
                                        className="w-full mt-2 px-3 py-2 text-gray-500 bg-transparent outline-none border  shadow-sm rounded-lg"
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
                                type="button"
                                className="ml-1 inline-block rounded bg-primary px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]"
                                data-te-ripple-init=""
                                data-te-ripple-color="light"
                            >
                                Update
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>


    )
}

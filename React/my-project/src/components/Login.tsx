import React from 'react'
import { useForm } from 'react-hook-form';
import { loginschema } from './loginschema';
import { yupResolver } from '@hookform/resolvers/yup';

import './register.css'; // You can create a CSS file for Register component styles
import { NavLink, useNavigate } from 'react-router-dom';
import Navbar from './Navbar';
import { getValue } from '@testing-library/user-event/dist/utils';
import { UserContext } from './UserContext';

type FormType = {
    username: string;
    password: string;
};
function Login() {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
            username: "",
            password: "",
        },
        resolver: yupResolver(loginschema),
    });

    const onSubmit = (data: FormType) => {
        alert(JSON.stringify(data));
        console.log("logged"+ data.username);
        navigate("/dashboard", {state: { username : data.username, password:data.password }})
            
        
    };

    return (
        <>
            <Navbar />
            <div className="flex justify-center items-center h-screen">
                <form onSubmit={handleSubmit(onSubmit)} className="bg-white shadow-md rounded px-8 pt-6 pb-8 form-container">
                    <h2 className="block text-gray-700 text-3xl font-bold mb-5">Login</h2>
                    <section className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="username"> Enter Username</label>
                        <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder="Username" {...register("username")} />
                        <span className="text-red-500 text-xs italic">{errors.username?.message}</span>
                    </section>
                    <section className='mb-4'>
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="password" > Enter Password</label>
                        <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password" placeholder="******************" {...register("password")} />
                        <span className='text-red-500 text-xs italic'>{errors.password?.message}</span>
                    </section>
                    
                    <section className="flex items-center justify-between">
                        <input className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type='submit' value={"Submit"}/>
                        <span className='text-sm  text-gray-700'>Don't have account? <NavLink to={"/register"} className="text-blue-500">Register Here</NavLink></span>
                    </section>
                </form>
            </div>
        </>
        
    );
}

export default Login;

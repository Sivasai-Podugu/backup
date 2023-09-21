import React from 'react';
import "../App.css";
import { useForm } from 'react-hook-form';
import { schema } from './schema';
import { yupResolver } from '@hookform/resolvers/yup';

import './register.css'; // You can create a CSS file for Register component styles
import { NavLink } from 'react-router-dom';

type FormType = {
    username: string;
    password: string;
    age: number;
};
function Register() {
    const { register, handleSubmit, formState: { errors } } = useForm({
        defaultValues: {
            username: "",
            password: "",
            age: 18,
        },
        resolver: yupResolver(schema),
    });

    const onSubmit = (data: FormType) => {
        alert(JSON.stringify(data));
    };

    return (
        <div className="flex justify-center items-center h-screen">
            <form onSubmit={handleSubmit(onSubmit)} className="bg-white shadow-md rounded px-8 pt-6 pb-8 form-container">
                <h2 className="block text-gray-700 text-3xl font-bold mb-5">Register</h2>
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
                <section className='mb-8'>
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="age"> Enter Age</label>
                    <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="age" type='number' placeholder='age' {...register("age")} />
                    <span className='text-red-500 text-xs italic'>{errors.age?.message}</span>
                </section>
                <section className="flex items-center justify-between">
                    <input className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type='submit' value={"Register"}/>
                    <span className='text-sm  text-gray-700'>Already have account? <NavLink to={"/login"} className="text-blue-500">Login Here</NavLink></span>
                </section>
            </form>
        </div>
    );
}

export default Register;

import React from 'react';
import logo from './logo.svg';
import './App.css';
import Login from './components/Login';
import Register from './components/Register';
import {BrowserRouter, Route, Routes} from "react-router-dom"
import NavBar from './components/Navbar';
import NoPage from './components/NoPage';
import Dashboard from './components/Dashboard';

function App() {
  return (
    <div className="App">
      {/* <h1 className="text-3xl font-bold underline  ">
        Hello world!
      </h1> */}
      {/* <Register /> */}

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<NavBar />} >
            <Route index element={<Login />} />
            <Route path='register' element={<Register />} />
            <Route path='login' element={<Login />} />
            <Route path='dashboard' element={<Dashboard  />} />
            <Route path="*" element={<NoPage />} />
          </Route>
          {/* <Route path='register' element={<Register />} />
          <Route path='login' element={<Login />} /> */}
  
        </Routes>
      </BrowserRouter>

      

      {/* <Login /> */}

      
    </div>
  );
}

export default App;

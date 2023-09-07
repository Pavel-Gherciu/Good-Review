import React, { Component, Fragment, useEffect, useState, preventDefault } from "react";
import Navbar from './Navbar';
import Landing from './Landing';
import Sidebar from './Sidebar';
import Footer from './Footer';
import { useNavigate } from 'react-router-dom';



const Home = ({setAuth}) => {

  const [name, setName] = useState("");

  async function getName(){
    const token2 = "Bearer_" + localStorage.token
    try {
      const response = await fetch("http://localhost:8080/dashboard",{
        method: "GET",
        headers: { Authorization : token2}
      });

      const parseRes = await response.json();

      setName(parseRes.username);
    } catch (err) {
      console.error(err.message);
    }
  }

  const [logged, setLog] = useState(true);

  const logout = async e =>{
    e.preventDefault();
    try {
      localStorage.removeItem("token");
      setName("");
      setAuth(false);
      setLog(false);
    } catch (err) {
      console.error(err.message);
    }
  };

  const navigate = useNavigate();

  useEffect(() =>{
    if("token" in localStorage)
      getName();
  },[logged]);



  return (
    <>
      <Navbar setAuth={setAuth} name={name} logout = {e => logout(e)}/>
      <Landing />
      <Sidebar /> 
      <Footer />
    </>
  )
}

export default Home
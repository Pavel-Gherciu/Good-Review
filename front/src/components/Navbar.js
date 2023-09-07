import React, { Component, Fragment, useEffect, useState, setState, preventDefault } from "react";
import logo from '../images/logo.png';
import { Route, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import {UserInt} from './UserInt';

const Navbar = ({setAuth, name, logout}) => {

  const navigate = useNavigate()

  return (
    <header>
      <div>
        <ul className='navbar-list'>
          <li className='navbar-element navbar-right' onClick={() => {navigate("/")}}>
            <img src={logo} alt=""  className='logo-img'/>
          </li>
          <li className='navbar-element navbar-left'>
            <input type="text" name="" id="search-bar" placeholder='Search...' />
          </li>
          <li className='navbar-element navbar-left'>
          {name? <UserInt name={name} logout={logout}/> : <div className='sign-in-button' onClick={() => {navigate("/login")}}>SIGN IN</div>}
          </li>
        </ul>
      </div>
    </header>
  )
}

export default Navbar;
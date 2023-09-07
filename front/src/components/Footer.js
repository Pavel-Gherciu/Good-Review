import React, { Component, Fragment, useEffect, useState, setState, preventDefault } from "react";
import logo from '../images/footer-logo.png';
import { Route, Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import {UserInt} from './UserInt';

const Footer = () => {

  const navigate = useNavigate()

  return (
    <footer>
      <div className="footer-container">
        <div className="footer-tab-icon">
          <img src={logo} alt="" />
        </div>
        <div className="footer-tab-links">
          <div className="footer-tab-title">
            Project
          </div>
          <a href="" className="footer-tab-link">
            About us
          </a>
          <a href="" className="footer-tab-link">
            Contacts
          </a>
        </div>
        <div className="footer-tab-links">
          <div className="footer-tab-title">
            Resources
          </div>
          <a href="" className="footer-tab-link">
            React
          </a>
          <a href="" className="footer-tab-link">
            Node.js
          </a>
          <a href="" className="footer-tab-link">
            Springboot
          </a>
          <a href="" className="footer-tab-link">
            MySQL
          </a>
        </div>
        <div className="footer-tab-links">
          <div className="footer-tab-title">
            Developers
          </div>
          <div className="footer-tab-link">
            Pavel Gherciu
          </div>
          <div className="footer-tab-link">
            Murașov David
          </div>
          <div className="footer-tab-link">
            Mațarin Vlaicu
          </div>
        </div>
      </div>
    </footer>
  )
}

export default Footer;
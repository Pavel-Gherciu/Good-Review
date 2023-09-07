import React, { Component, Fragment, useEffect, useState, preventDefault } from "react";
import categ from '../images/temp-categ.png';
import worldicon from '../images/world-icon.png';
import bookmark from '../images/bookmark.png';
import github from '../images/github.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faBuildingColumns, faUtensils, faShoppingCart, faShirt} from '@fortawesome/free-solid-svg-icons';
import Tooltip from '@mui/material/Tooltip';
import { useNavigate } from 'react-router-dom';

function Sidebar() {


  const [btnState, setBtnState] = useState(false);
  
 const handleBtnClick = (e) => {
   setBtnState((prev) => !prev);
 };

  const navigate = useNavigate();

  return (
    <aside>
      <ul className='sidebar-list'>
        <li className={btnState ? "sidebar-element-active sidebar-element" : "sidebar-element"} onClick={(e) => handleBtnClick(e)}>
          <div>
            <img src={categ} alt="" />
          </div>
        </li>
        <li className='sidebar-element'>
          <div>
            <img src={worldicon} alt="" />
          </div>
        </li>
        <li className='sidebar-element'>
          <div>
            <img src={bookmark} alt="" />
          </div>
        </li>
        <li className='sidebar-element'>
          <div>
            <img src={github} alt="" />
          </div>
        </li>
      </ul>
      <div className={btnState ? "show-popup-tab" : "popup-tab"}>
        <ul className="sidebar-category-container">
          <Tooltip title="Banks" placement="top-start" enterDelay={100} leaveDelay={100}>
            <li className="sidebar-category" onClick = {() => navigate("/category/1")}>
              <FontAwesomeIcon icon={faBuildingColumns} className="sidebar-icon" />
            </li>    
          </Tooltip>
          <Tooltip title="Restaurants" placement="top-start" enterDelay={100} leaveDelay={100}>
            <li className="sidebar-category" onClick = {() => navigate("/category/2")}>
              <FontAwesomeIcon icon={faUtensils} className="sidebar-icon" />
            </li>    
          </Tooltip>
          <Tooltip title="Supermarkets" placement="top-start" enterDelay={100} leaveDelay={100}>
            <li className="sidebar-category" onClick = {() => navigate("/category/3")}>
              <FontAwesomeIcon icon={faShoppingCart} className="sidebar-icon" />
            </li>    
          </Tooltip>
          <Tooltip title="Clothing stores" placement="top-start" enterDelay={100} leaveDelay={100}>
            <li className="sidebar-category" onClick = {() => navigate("/category/4")}>
              <FontAwesomeIcon icon={faShirt} className="sidebar-icon" />
            </li>    
          </Tooltip>
        </ul>
      </div>
    </aside>
  )
}

export default Sidebar
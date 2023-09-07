import React from 'react'
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import {Rating} from '@mui/material';
import { json, useNavigate } from 'react-router-dom' ;
import {useState, useEffect} from 'react';
import {TextField, FormControlLabel, Checkbox, Box, Button} from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import StarIcon from '@mui/icons-material/Star';
import { useForm, Controller } from "react-hook-form";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

export const ReviewTab = (item) =>{
  const navigate = useNavigate();

  const [username, setName] = useState("");

  const getName = async() => {
    try {
      
      const response = await fetch(`http://localhost:8080/users/${item.user_id}`)
      const jsonData = await response.json();

      setName(jsonData.username)
    } catch (err) {
      console.error(err.message);
    }
  }

  useEffect(()=>{
    getName();
  },[]);

  return(
    <div key={item.index} className='comp-review-container'>
      <div className='comp-review-wrapper-up'>
        <AccountCircleIcon sx={{fontSize:"40px", color:"#212121", marginRight:"10px"}} />
        {username} 
        <Rating name="half-rating-read" defaultValue={item.rating} precision={0.5} readOnly size="large" sx={{marginLeft:"20px", marginRight:"20px"}}/>
        {item.date}
      </div>
      <div className='comp-review-text'>
        {item.text} 
      </div>
      <img src={item.image} alt="" className='comp-review-img'/>
    </div>
  )
}
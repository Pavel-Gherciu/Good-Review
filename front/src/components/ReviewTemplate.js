import React, { Component, Fragment, useEffect, useState } from "react";
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import logo from '../images/login-logo.png';
import company_logo from '../images/maib.png';
import {TextField,  Rating, Button, FormControlLabel} from '@mui/material';
import { borderRadius } from '@mui/system';
import { useForm, Controller } from "react-hook-form";
import { useNavigate } from 'react-router-dom';

const ReviewTemplate = ({setAuth, user_id, name, logout, logged, company_id, title}) => {

  const {control, handleSubmit} = useForm();

  const navigate = useNavigate();

  const onSubmitForm = async (data) =>{
    console.log(data)
    try {

      const body = data

      const response = await fetch(`http://localhost:8080/reviews`,
      
      {
        method: "POST",
        headers: {"Content-Type" : "application/json"},
        body: JSON.stringify(body)
      });

      navigate("/"); 
      
    } catch (err) {
      console.error(err.message);
    }
  }



  const onSubmitHandler = (data) => {
    data.companyId = JSON.stringify(company_id);
    data.userId = JSON.stringify(user_id)
    onSubmitForm(data);
  };


  return (
    <>
    <Navbar  setAuth={setAuth} name={name} logout={logout} />
    <Sidebar></Sidebar>
    <section className='review-bg'>
      <div className='review-img-cover'>
      </div>
      <div className='review-content-container'>
        <img src={logo} alt="" />
        <div className='review-wrapper'>
          <form className='form' noValidate onSubmit={handleSubmit((data) => onSubmitHandler(data))}>
            <div className='review-title'>{title}</div>
            <div className='review-rating-title'>Select your rating</div>
            <div className='rating-container'>
            <Controller
              control={control}
              name="rating"
              defaultValue={"0"}
              rules={{ required: true }}
              render={({field}) => <Rating {...field} precision={0.5} size="large" onChange={(e) => field.onChange(parseFloat(e.target.value))}/>}
            />
            </div>
            <Controller
              control={control}
              name="text"
              defaultValue=""
              render={({field}) => (
                <TextField
                  {...field}
                  id="outlined-multiline-static"
                  multiline
                  rows={11}
                  fullWidth
                />
              )}
            />
            <Controller
              control={control}
              name="image"
              defaultValue=""
              render={({field}) => (
                <TextField
                  {...field}
                  id="outlined-multiline-static"
                  className="review-image-writing"
                  label="Attach an image url (optional)"
                  sx={{marginTop:"20px", width:"100%"}}
                />
              )}
            />
            <Button type="submit"  variant="contained" className='login-button' sx={{backgroundColor:'#212121','&:hover': {backgroundColor: '#616161'}, marginTop:"20px"}}>
              Post review
            </Button>
          </form>
        </div>
      </div>
    </section>
    </>
  )
}

export default ReviewTemplate
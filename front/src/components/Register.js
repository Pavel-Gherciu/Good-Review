import React, { Component, Fragment, useEffect, useState } from "react";
import logo from '../images/login-logo.png';
import { InputAdornment, TextField, IconButton, Box, FormControlLabel, Checkbox, Typography, Button, Link } from '@mui/material';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import CheckIcon from '@mui/icons-material/Check';
import { useNavigate } from 'react-router-dom' ;
import { useForm, Controller } from "react-hook-form";
import Joi from 'joi';
import {joiResolver} from "@hookform/resolvers/joi"

const Register = ({setAuth}) => {


  
const validationSchema = Joi.object({

  firstName: Joi.string().required().messages({
    'string.empty':'Please enter your first name',
  }),
  lastName: Joi.string().required().messages({
    'string.empty':'Please enter your last name',
  }),

  username: Joi.string().required().messages({
    'string.empty':'Please enter a username',
  }),
  email: Joi.string().required().email({  tlds: { allow: false } }).messages({
    'string.empty':'Please enter an email',
    'string.email':'Email must be valid'
  }),
  password: Joi.string().required().min(7).messages({
    'string.empty':'Please enter a password',
    'string.min':'Password must have at least 7 characters'
  }),
});


  const [shouldShowPassword, setShouldShowPassword] = useState(false)
  

  const toggleVisibility = () =>{
    setShouldShowPassword(prevValue => !prevValue)
  }

  const navigate = useNavigate();

  const onSubmitForm = async (data) =>{
    console.log(data)
    try {

      const body = data

      const response = await fetch("http://localhost:8080/register",
      
      {
        method: "POST",
        headers: {"Content-Type" : "application/json"},
        body: JSON.stringify(body)
      });

      const parseRes = await response.json();
      console.log(parseRes.message)

      if(parseRes.token){
        localStorage.setItem("token", parseRes.token);
        navigate("/login");
      }else{
        console.log(parseRes.message)
        toggleErrors(parseRes.message);
      }

      
      
    } catch (err) {
      console.error(err.message);
    }
  }

  const [server_errors, setServerErrors] = useState("")
  

  const toggleErrors = (error) =>{
    setServerErrors(error)
  }


  const {control, handleSubmit, formState:{errors}} = useForm({
    resolver: joiResolver(validationSchema)
  });
  
  
  const onSubmit = data => {
    onSubmitForm(data);
  }


  return (
    <section className='login-bg'>
      <div className='login-img-cover'>
      </div>
      <div className='login-content-container'>
        <div className='login-desc-section' onClick={() => navigate("/")}>
          <img src={logo} alt="" />
          <div className='login-desc-container'>
            <div className='login-desc-title'>
              INCEPE CU NOI...
            </div>
            <div className='login-desc-text'>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </div>
          </div>
        </div>
        <div className='login-field'>
          <div className='login-field-container'>
            <div className='login-field-title'>
              Sign up now
            </div>
            <form className='form' onSubmit={handleSubmit(onSubmit)}>
              <Box className='namebox' display="flex">
                <Controller
                  control={control}
                  name="firstName"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      className='login-textfield' 
                      required={true} 
                      label="First Name" 
                      error={!!errors.firstName}
                      helperText={errors.firstName?.message}
                      autoComplete="off" 
                      variant="outlined"
                      //value = {firstname}
                      //onChange={e => onChange(e)}
                      >
                    </TextField>
                  )}
                />
                <Controller
                  control={control}
                  name="lastName"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      autoComplete='off' 
                      className='login-textfield' 
                      required={true} 
                      label="Last Name" 
                      error={!!errors.lastName}
                      helperText={errors.lastName?.message}
                      variant="outlined"
                      //value = {lastname}
                      //onChange={e => onChange(e)}
                      >
                    </TextField>
                  )}
                />
              </Box>
              <Controller
                control={control}
                name="username"
                defaultValue=""
                render={({field}) => (
                <TextField 
                    {...field}
                    variant="outlined" 
                    autoComplete="off" 
                    required={true} 
                    label="Name" 
                    fullWidth={true} 
                    error={!!errors.username}
                  helperText={errors.username?.message}
                    className='login-textfield'>
                  </TextField>
                )}
              />
              <Controller
              control={control}
              name="email"
              defaultValue=""
              render={({field}) => (
                <TextField
                  {...field} 
                  variant="outlined" 
                  autoComplete="off" 
                  label="Email Address" 
                  required={true} 
                  fullWidth={true}
                  error={!!errors.email}
                  helperText={errors.email?.message} 
                  className='login-textfield'>
                </TextField>
                )}
              />
              <Controller
              control={control}
              name="password"
              defaultValue=""
              render={({field}) => (
                <TextField 
                  InputProps={{
                    endAdornment: (
                      <InputAdornment position="end">
                        <IconButton onClick={toggleVisibility}>
                          {shouldShowPassword ? <VisibilityIcon /> : <VisibilityOffIcon />}
                        </IconButton>
                      </InputAdornment>
                    )}
                  }
                  {...field} 
                  type={shouldShowPassword ? "text" : "password"}  
                  required={true} 
                  variant="outlined" 
                  label="Password" 
                  fullWidth={true} 
                  autoComplete='off'
                  error={!!errors.password}
                  helperText={errors.password?.message} 
                  className='login-textfield'>
                </TextField>
              )}
              />
              <div className='server-errors'>{server_errors}</div>
              <Box className='button-wrapper'>
                <Button type="submit" fullWidth={true} variant="contained" className='login-button' sx={{backgroundColor:'#212121','&:hover': {backgroundColor: '#616161'}}}>
                  SIGN UP
                </Button>
              </Box>
              <Box className='register-wrapper'>
                <Typography variant='body1'>
                  Already have an account? &nbsp;
                </Typography>
                <Link className='link' underline="none">
                  <Typography variant="body1" underline="none" sx={{fontWeight:'700'}} onClick = {() => navigate("/login")}>Sign In</Typography>
                </Link>
              </Box>
            </form>
          </div>
        </div>
      </div>
    </section>
  )
}

export default Register
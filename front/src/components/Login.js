import React, { Component, Fragment, useEffect, useState } from "react";
import logo from '../images/login-logo.png';
import { InputAdornment, TextField, IconButton, Box, FormControlLabel, Checkbox, Typography, Button, Link } from '@mui/material';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import CheckIcon from '@mui/icons-material/Check';
import { useNavigate } from 'react-router-dom' ;
import { useForm, Controller } from "react-hook-form";
import Joi from 'joi';
import {joiResolver} from "@hookform/resolvers/joi";


const validationSchema = Joi.object({
  email: Joi.string().required().email({  tlds: { allow: false } }).messages({
    'string.empty':'Please enter an email',
    'string.email':'Email must be valid'
  }),
  password: Joi.string().required().messages({
    'string.empty':'Please enter a password',
  })
});

const Login = ({setAuth}) => {


  const onSubmitForm = async (data) =>{
    try {

      const body = data

      const response = await fetch("http://localhost:8080/login",
      
      {
        method: "POST",
        headers: {"Content-Type" : "application/json"},
        body: JSON.stringify(body)
      });

      const parseRes = await response.json();

      if(parseRes.token){
        setAuth(true);
        localStorage.setItem("token", parseRes.token);
      }else{
        setAuth(false);
        //toast.error(parseRes);
        toggleErrors(parseRes.message);
      }
      
    
    } catch (err) {
      console.error(err.message);
    }
  }
  
  const [shouldShowPassword, setShouldShowPassword] = useState(false)
  

  const toggleVisibility = () =>{
    setShouldShowPassword(prevValue => !prevValue)
  }

  const [server_errors, setServerErrors] = useState("")
  

  const toggleErrors = (error) =>{
    setServerErrors(error)
  }

  const navigate = useNavigate();

  const {control, handleSubmit, formState:{errors}} = useForm({
    resolver: joiResolver(validationSchema),
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
              Sign in now
            </div>
            <form className='form' onSubmit={handleSubmit(onSubmit)}>
              <Controller
              control={control}
              name="email"
              defaultValue=""
              render={({field}) => (
                <TextField 
                  {...field}
                  error={!!errors.email}
                  helperText={errors.email?.message}
                  variant="outlined" 
                  autoComplete="off" 
                  label="Email Address" 
                  fullWidth={true} 
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
                  error={!!errors.password}
                  helperText={errors.password?.message}
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
                  variant="outlined" 
                  label="Password" 
                  fullWidth={true} 
                  autoComplete='off' 
                  className='login-textfield'>
                </TextField>
                )}
              />
              <Box display="flex" justifyContent="space-between" alignItems="center">
                <FormControlLabel label="Remember me" className='checkbox' control={<Checkbox 
                  checkedIcon={
                    <CheckIcon style={{fontSize: 24, backgroundColor: '#E0E0E0', color:"#9E9E9E", borderRadius: '4px', border: '1px solid white'}}/>
                  }
                />}/>
                <Link className='link' underline="none">
                  <Typography variant='body1' underline="none">Forgot your password?</Typography>
                </Link>
              </Box>
              <div className='server-errors'>{server_errors}</div>
              <Box className='button-wrapper'>
                <Button type="submit" fullWidth={true} variant="contained" className='login-button' sx={{backgroundColor:'#212121','&:hover': {backgroundColor: '#616161'}}}>
                  SIGN IN
                </Button>
              </Box>
              <Box className='login-wrapper'>
                <Typography variant='body1'>
                  Don't have an account? &nbsp;
                </Typography>
                <Link className='link' underline="none">
                  <Typography variant="body1" underline="none" sx={{fontWeight:'700'}} onClick = {() => navigate("/register")}>Sign Up</Typography>
                </Link>
              </Box>
            </form>
          </div>
        </div>
      </div>
    </section>
  )
}

export default Login
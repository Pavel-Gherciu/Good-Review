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

const AddCompany = ({setAuth, category_id, category_name}) => {


  
const validationSchema = Joi.object({

  title: Joi.string().required().messages({
    'string.empty':'Please enter a title',
  }),

  logo: Joi.string().required().messages({
    'string.empty':'Please enter a logo link',
  }),

  topImg: Joi.string().required().messages({
    'string.empty':'Please enter an image link',
  }),

  city: Joi.string().required().messages({
    'string.empty':'Please enter a city',
  }),
  location: Joi.string().required().messages({
    'string.empty':'Please enter a location',
  }),

  site: Joi.string().required().messages({
    'string.empty':'Please enter a site',
  }),

  siteLink: Joi.string().required().messages({
    'string.empty':'Please enter a site link',
  }),

  schedule: Joi.string().required().messages({
    'string.empty':'Please enter the schedule',
  }),

  phone: Joi.string().required().messages({
    'string.empty':'Please enter a phone number',
  }),

  route: Joi.string().required().messages({
    'string.empty':'Please enter the schedule',
  }),

  about: Joi.string().required().messages({
    'string.empty':'Please enter the schedule',
  }),

});



  const navigate = useNavigate();

  const onSubmitForm = async (data) =>{
    console.log(data)
    try {

      const body = data

      const response = await fetch(`http://localhost:8080/companies/add/${category_id}`,
      
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

  const onKeyPresslogo = (e) => {
    if (e.key === "Enter") {
      document.querySelector(".preview-image-logo").src = e.target.value;
      e.preventDefault();
    }
  }

  const onKeyPressbig = (e) => {
    if (e.key === "Enter") {
      document.querySelector(".preview-image-big").src = e.target.value;
      e.preventDefault();
    }
  }

  return (
    <section className='ac-bg'>
      <div className='login-img-cover'>
      </div>
      <div className='ac-content-container'>
        <div className='ac-field'>
          <div className='login-field-container'>
            <div className='login-field-title'>
              Create {category_name} Company
            </div>
            <form className='form' onSubmit={handleSubmit(onSubmit)}>
              <Controller
                control={control}
                name="title"
                defaultValue=""
                render={({field}) => (
                <TextField 
                    {...field}
                    variant="outlined" 
                    autoComplete="off" 
                    required={true} 
                    label="Title" 
                    fullWidth={true} 
                    error={!!errors.title}
                    helperText={errors.title?.message}
                    className='login-textfield'>
                  </TextField>
                )}
              />
              <Controller
                control={control}
                name="logo"
                defaultValue=""
                render={({field}) => (
                <TextField 
                    {...field}
                    variant="outlined" 
                    autoComplete="off" 
                    required={true} 
                    label="Logo link" 
                    fullWidth={true} 
                    error={!!errors.logo}
                    helperText={errors.logo?.message}
                    className='login-textfield'
                    onKeyPress={onKeyPresslogo}
                    >
                  </TextField>
                )}
              />
              <div className='preview-container'>
                Preview (Press Enter in the field above)
                <img src="" className='preview-image-logo' />
              </div>
              <Controller
                control={control}
                name="topImg"
                defaultValue=""
                render={({field}) => (
                <TextField 
                    {...field}
                    variant="outlined" 
                    autoComplete="off" 
                    required={true} 
                    label="Top image link" 
                    fullWidth={true} 
                    error={!!errors.topImg}
                    helperText={errors.topImg?.message}
                    className='login-textfield'
                    onKeyPress={onKeyPressbig}
                    >
                  </TextField>
                )}
              />
              <div className='preview-container'>
                Preview (Press Enter in the field above)
                <img src="" className='preview-image-big' />
              </div>
              <Box className='namebox' display="flex">
                <Controller
                  control={control}
                  name="city"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      className='login-textfield' 
                      required={true} 
                      label="City" 
                      error={!!errors.city}
                      helperText={errors.city?.message}
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
                  name="location"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      autoComplete='off' 
                      className='login-textfield' 
                      required={true} 
                      label="Location" 
                      error={!!errors.location}
                      helperText={errors.location?.message}
                      variant="outlined"
                      //value = {lastname}
                      //onChange={e => onChange(e)}
                      >
                    </TextField>
                  )}
                />
              </Box>
              <Box className='namebox' display="flex">
                <Controller
                  control={control}
                  name="site"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      className='login-textfield' 
                      required={true} 
                      label="Site" 
                      error={!!errors.site}
                      helperText={errors.site?.message}
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
                  name="siteLink"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      autoComplete='off' 
                      className='login-textfield' 
                      required={true} 
                      label="Site link" 
                      error={!!errors.siteLink}
                      helperText={errors.siteLink?.message}
                      variant="outlined"
                      //value = {lastname}
                      //onChange={e => onChange(e)}
                      >
                    </TextField>
                  )}
                />
              </Box>
              <Box className='namebox' display="flex">
                <Controller
                  control={control}
                  name="schedule"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      className='login-textfield' 
                      required={true} 
                      label="Schedule" 
                      error={!!errors.schedule}
                      helperText={errors.schedule?.message}
                      autoComplete="off" 
                      variant="outlined"
                      >
                    </TextField>
                  )}
                />
                <Controller
                  control={control}
                  name="phone"
                  defaultValue=""
                  render={({field}) => (
                    <TextField 
                      {...field}
                      autoComplete='off' 
                      className='login-textfield' 
                      required={true} 
                      label="Phone number" 
                      error={!!errors.phone}
                      helperText={errors.phone?.message}
                      variant="outlined"
                      >
                    </TextField>
                  )}
                />
              </Box>
              <Controller
                control={control}
                name="route"
                defaultValue=""
                render={({field}) => (
                <TextField 
                    {...field}
                    variant="outlined" 
                    autoComplete="off" 
                    required={true} 
                    label="Route (begin with /)" 
                    fullWidth={true} 
                    error={!!errors.username}
                  helperText={errors.username?.message}
                    className='login-textfield'>
                  </TextField>
                )}
              />
              <Controller
              control={control}
              name="about"
              defaultValue=""
              render={({field}) => (
                <TextField
                  {...field}
                  required={true} 
                  multiline
                  maxRows={5}
                  variant="outlined" 
                  autoComplete='off' 
                  label="About company" 
                  fullWidth={true} 
                  error={!!errors.about}
                  helperText={errors.about?.message}
                  className='reg-textfield'>
                </TextField>
                )}
              />
              <div className='server-errors'>{server_errors}</div>
              <Box className='button-wrapper'>
                <Button type="submit" fullWidth={true} variant="contained" className='login-button' sx={{backgroundColor:'#212121','&:hover': {backgroundColor: '#616161'}}}>
                  ADD COMPANY
                </Button>
              </Box>
              <Box className='register-wrapper'>
                <Typography variant='body1'>
                  Changed your mind? &nbsp;
                </Typography>
                <Link className='link' underline="none">
                  <Typography variant="body1" underline="none" sx={{fontWeight:'700'}} onClick = {() => navigate("/")}>Go back</Typography>
                </Link>
              </Box>
            </form>
          </div>
        </div>
      </div>
    </section>
  )
}

export default AddCompany
import React from 'react'
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import {Rating} from '@mui/material';
import { useNavigate } from 'react-router-dom' ;
import {useState, useEffect} from 'react';
import {TextField, FormControlLabel, Checkbox, Box, Button} from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import StarIcon from '@mui/icons-material/Star';
import { useForm, Controller } from "react-hook-form";

export const CompanyTab = (item) =>{
  const navigate = useNavigate();

  const deleteCompany = async id => {
    try {
      const deleteCompany = await fetch(`http://localhost:8080/companies/${id}`,{
        method: "DELETE"
      });
      console.log(item)
      item.stateChanger(id);
    } catch (err) {
      console.log(err.message);
    }
  }

  const {control, handleSubmit} = useForm();
  const [reviews, setReviews] = useState([]);
  const [rating, setRating] = useState('0');

  const getReviews = async() => {
    try {
      
      const response = await fetch(`http://localhost:8080/reviews/readall/company/${item.companyId}`)
      const jsonData = await response.json();

      setReviews(jsonData);
      const rating_array = [];
      jsonData.forEach(element => {
        rating_array.push(element.rating)
      });

      const full_rating = rating_array.reduce((a, b) => a + b, 0) / rating_array.length;
      if(isNaN(full_rating)){
        setRating(0);
      }else{
        setRating(full_rating)
      }
    } catch (err) {
      console.error(err.message);
    }
  }

  useEffect(()=>{
    getReviews();
    console.log(parseFloat(rating));
  },[rating, item.companyId]);

  if(parseFloat(rating) >= item.changedRating)
  return(
    <div className='cat-reviews-templates' key={item.index}>
      <div className='cat-details-container' onClick={() => navigate(item.route)}>
        <div className='cat-details-img'>
          <img src={item.logo} alt=""/>
        </div>
        <div className='cat-details-info'>
          <div className='cat-details-title'>
            {item.title}
          </div>
          <div className='cat-rating-container'>
            <Controller
                control={control}
                name="rating"
                defaultValue={"0"}
                rules={{ required: true }}
                render={({field}) => <Rating {...field} name="half-rating-read" value={parseFloat(rating)} precision={0.5} readOnly/>}
            />
            <div className='cat-rating-text'>|</div>
            <div className='cat-rating-text'>{item.reviews} reviews</div>
          </div>
          <div className='cat-details-city'>{item.city}</div>
        </div>
      </div>
      <div className='cat-contacts-container'>
        <div className='cat-contacts-left'>
          <a href={item.siteLink} className='cat-contacts-text'>{item.site}</a>
          <div className='cat-contacts-text'>|</div>
          <div className='cat-contacts-text'>{item.category_name}</div>
          <Box className='edit-company-wrapper'>
            <Button variant="contained" sx={{height: "24px" ,backgroundColor:'#66CDBD','&:hover': {backgroundColor: '#66cd98'}}} onClick={() => navigate(`/edit_company/${item.companyId}`)}>
              EDIT COMPANY
            </Button>
          </Box>
          <Box className='edit-company-wrapper'>
            <Button variant="contained" sx={{height: "24px" ,backgroundColor:'#F1505E','&:hover': {backgroundColor: '#fb3646'}}} onClick={()=>deleteCompany(item.companyId)}>
              DELETE COMPANY
            </Button>
          </Box>
        </div>
        <div className='cat-contacts-right'>
          {item.location}
        </div>
      </div>
    </div>
  )
}
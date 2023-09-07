import React, {createContext} from 'react'
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import Footer from './Footer';
import {Rating, Button, Link} from '@mui/material';
import StarOutlineIcon from '@mui/icons-material/StarOutline';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
import PhoneOutlinedIcon from '@mui/icons-material/PhoneOutlined';
import ExploreOutlinedIcon from '@mui/icons-material/ExploreOutlined';
import { TempComments } from './TempComments';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { json, useNavigate } from 'react-router-dom';
import {useState, useEffect} from 'react';
import { useForm, Controller } from "react-hook-form";
import { ReviewTab } from './ReviewTab';

const CompanyTemplate = ({setAuth, name, logout, company_id ,title, logo, top_img, about, schedule, site_link, phone, location}) => {

  const {control, handleSubmit} = useForm();

  const navigate = useNavigate();

  const [reviews, setReviews] = useState([]);

  const [rating, setRating] = useState();

  const getReviews = async() => {
    try {
      
      const response = await fetch(`http://localhost:8080/reviews/readall/company/${company_id}`)
      const jsonData = await response.json();

      setReviews(jsonData);
      const rating_array = [];
      jsonData.forEach(element => {
        rating_array.push(element.rating)
      });

      const full_rating = rating_array.reduce((a, b) => a + b, 0) / rating_array.length;
      setRating(full_rating)
    } catch (err) {
      console.error(err.message);
    }
  }

  useEffect(()=>{
    getReviews();
  },[rating]);

  return (
    <>
    <Navbar setAuth={setAuth} name={name} logout = {logout} />
    <Sidebar />
    <section className='comp-main'>
      <div className='comp-intro-img' style={{backgroundImage: `url(${top_img})`}}>
      </div>
      <div className='comp-main-content'>
        <div className='comp-main-container'>
          <div className='comp-intro-container'>
            <img src={logo} alt="" className='comp-intro-logo' />
            <div className='comp-title-container'>
              <div className='comp-title'>{title}</div>
              <div className='comp-under-title'>
                <div>
                  Verified
                </div>
                <div>
                  Open {schedule}
                </div>
              </div>
            </div>
          </div>
           <div className='comp-info-container'>
            <Controller
                control={control}
                name="rating"
                defaultValue={"0"}
                rules={{ required: true }}
                render={({field}) => <Rating {...field} name="half-rating-read" value={parseFloat(rating)} precision={0.5} readOnly size="large" sx={{fontSize: "3rem"}}/>}
            />
            <div className='comp-review-nr'>000 Reviews</div>
            <Button variant="contained" size="medium" startIcon={<StarOutlineIcon />} onClick={() => navigate(`/review/${company_id}`)}>
              Write review
            </Button>
            <Button variant="outlined" size="medium" startIcon={<FavoriteBorderIcon />}>
              Follow
            </Button>
          </div>
          <div className='comp-about-container'>
            <div className='comp-about-title'>About the Business</div>
            <div className='comp-about-content'>
              {about}
            </div>
          </div>
          <div className='comp-about-container'>
            <div className='comp-about-title'>Reviews</div>
            <div className='comp-about-content comp-review-sorter'>
              {reviews.map((item, index)=>{
                return (
                  <ReviewTab 
                  index = {index}
                  user_id = {item.userId}
                  date = {item.date}
                  text = {item.text}
                  image = {item.image}
                  rating = {item.rating}
                  />
                )
              })}  
            </div>
          </div>
        </div>
        <div className='comp-side-container'>
          <div className='comp-side-wrapper'>
            <div className='comp-side-link'>
              <Link href={site_link} underline="none" sx={{color: "#347CCA", fontWeight:"600"}}>
                {site_link}
              </Link>
              <OpenInNewIcon sx={{fontSize:"34px", color:"#212121"}}/>
            </div>
            <div className='comp-side-phone'>
              {phone}
              <PhoneOutlinedIcon sx={{fontSize:"30px", color:"#212121"}}/>
            </div>
            <div className='comp-side-location'>
              <div>
                {location}
              </div>
              <ExploreOutlinedIcon sx={{fontSize:"34px", color:"#212121"}}/>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </section>
    </>
  )
}

export default CompanyTemplate;
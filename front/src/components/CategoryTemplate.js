import React from 'react'
import Navbar from './Navbar';
import Footer from './Footer';
import Sidebar from './Sidebar';
import {Rating} from '@mui/material';
import { useNavigate } from 'react-router-dom' ;
import {useState, useEffect} from 'react';
import {TextField, FormControlLabel, Checkbox, Box, Button} from '@mui/material';
import CheckIcon from '@mui/icons-material/Check';
import StarIcon from '@mui/icons-material/Star';
import { CompanyTab } from './CompanyTab';

const CategoryTemplate = ({setAuth, name, logout, logged, category_id, category_name, category_img}) => {

  const navigate = useNavigate();

  const [companies, setCompanies] = useState([]);

  const [updatedCompanies, setUpdatedCompanies] = useState([]);

  const getCompanies = async() => {
    try {
      
      const response = await fetch(`http://localhost:8080/companies/readall/${category_id}`)
      const jsonData = await response.json();

      setCompanies(jsonData);
    } catch (err) {
      console.error(err.message);
    }
  }

  const [anyRating, setRating] = useState(true);
  const [Rating35, setRating2] = useState(false);
  const [Rating40, setRating3] = useState(false);
  const [Rating45, setRating4] = useState(false);
  const [changedRating, changeRating] = useState(0);
  
 const handleBtnClick = (e) => {
   setRating((prev) => !prev);
   if(!anyRating){
    setRating2(false);
    setRating3(false);
    setRating4(false);
   }
   changeRating(0);
 };

 const handleBtnClick2 = (e) => {
  setRating2((prev) => !prev);
  if(!Rating35){
   setRating(false);
   setRating3(false);
   setRating4(false);
  }
  changeRating(3.0);
};

const handleBtnClick3 = (e) => {
  setRating3((prev) => !prev);
  if(!Rating40){
   setRating(false);
   setRating2(false);
   setRating4(false);
  }
  changeRating(4.0);
};

const handleBtnClick4 = (e) => {
  setRating4((prev) => !prev);
  if(!Rating45){
   setRating(false);
   setRating2(false);
   setRating3(false);
  }
  changeRating(4.5);
};

  const deleteCompany = async id => {
    try {
      const deleteCompany = await fetch(`http://localhost:8080/companies/${id}`,{
        method: "DELETE"
      });
      setUpdatedCompanies(id);
    } catch (err) {
      console.log(err.message);
    }
  }

  useEffect(()=>{
    getCompanies();
  },[logged, changedRating, category_id, updatedCompanies]);


  return (
    <>
    <Navbar setAuth={setAuth} name={name} logout={logout}/>
    <Sidebar /> 
    <section className='cat-bg'>
      <div className='cat-container'>
        <div className='cat-intro-container' style={{backgroundImage: `url(${category_img})`}}>
          <div className='cat-title-container'>
            <div className='cat-title'>{category_name} Reviews</div>
            <div className='cat-desc'>Compare the best companies in this category</div>
          </div>
        </div>
        <div className='cat-reviews-container'>
          <div className='cat-reviews-filters'>
            <div className='cat-reviews-filters-wrapper'>
              <div className='cat-filters-title'>
                Rating
                <div className='filters-rating-tab-container'>
                  <div className={anyRating ? "cat-filters-tab-clicked" : "cat-filters-tab"} onClick={(e) => handleBtnClick(e)}>Any</div>
                  <div className={Rating35 ? "cat-filters-tab-clicked" : "cat-filters-tab"} onClick={(e) => handleBtnClick2(e)}><StarIcon sx={{fontSize: "19px"}}/> 3.0+</div>
                  <div className={Rating40 ? "cat-filters-tab-clicked" : "cat-filters-tab"} onClick={(e) => handleBtnClick3(e)}><StarIcon sx={{fontSize: "19px"}}/> 4.0+</div>
                  <div className={Rating45 ? "cat-filters-tab-clicked" : "cat-filters-tab"} onClick={(e) => handleBtnClick4(e)}><StarIcon sx={{fontSize: "19px"}}/> 4.5+</div>
                </div>
              </div>
              <div className='cat-filters-title'>
                Location
                <TextField id="outlined-basic"  variant="outlined" size="small" />
              </div>
              <div className='cat-filters-title'>
                Company status
                <div>
                  <FormControlLabel label="Verified" className='checkbox' control={<Checkbox 
                    checkedIcon={
                      <CheckIcon style={{fontSize: 24, backgroundColor: '#E0E0E0', color:"#9E9E9E", borderRadius: '4px', border: '1px solid white'}}/>
                    }
                    />
                  }/>
                  <FormControlLabel label="Claimed" className='checkbox' control={<Checkbox 
                    checkedIcon={
                      <CheckIcon style={{fontSize: 24, backgroundColor: '#E0E0E0', color:"#9E9E9E", borderRadius: '4px', border: '1px solid white'}}/>
                    }
                    />
                  }/>
                </div>
                
              </div>
              <Box className='button-wrapper'>
                <Button fullWidth={true} variant="contained" className='login-button' sx={{backgroundColor:'#347CCA','&:hover': {backgroundColor: '#1E78E9'}}} onClick={() => navigate(`/add_company/${category_id}`)}>
                  ADD COMPANY
                </Button>
              </Box>
            </div>
          </div>
            <div className='cat-reviews-right'>
              {companies.map((item, index)=>{
                //if (item.title == "Victoriabank")
                return (
                  <CompanyTab
                  changedRating = {changedRating}
                  stateChanger={setUpdatedCompanies}
                  companyId = {item.companyId}
                  index = {index}
                  route = {item.route}
                  logo = {item.logo}
                  title = {item.title}
                  score = {item.score}
                  reviews = {item.reviews}
                  city = {item.city}
                  siteLink = {item.siteLink}
                  site = {item.site}
                  location = {item.location}
                  category_name={category_name}
                  />
                )
              })}
            </div>
        </div>
      </div>
      <Footer>
        
      </Footer>
    </section>
    </>
  )
}

export default CategoryTemplate
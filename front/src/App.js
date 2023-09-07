import './App.scss';
import Login from './components/Login';
import Register from './components/Register';
import {useState, useEffect} from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import Home from './components/Home';
import ReviewTemplate from './components/ReviewTemplate';
import CategoryTemplate from './components/CategoryTemplate';
import CompanyTemplate from './components/CompanyTemplate';
import { TempCategories } from './components/TempCategories';
import AddCompany from './components/AddCompany';
import EditCompany from './components/EditCompany';
import { useNavigate } from 'react-router-dom';


function App() {


  const [name, setName] = useState("");
  const [userId, setUserId] = useState("");

  async function getName(){
    const token2 = "Bearer_" + localStorage.token
    try {
      const response = await fetch("http://localhost:8080/dashboard",{
        method: "GET",
        headers: { Authorization : token2}
      });

      const parseRes = await response.json();

      setName(parseRes.username);
      setUserId(parseRes.id);
    } catch (err) {
      console.error(err.message);
    }
  }

  const [logged, setLog] = useState(true);

  const logout = async e =>{
    e.preventDefault();
    try {
      localStorage.removeItem("token");
      setName("");
      setUserId("");
      setAuth(false);
      setLog(false);
    } catch (err) {
      console.error(err.message);
    }
  };



  const isAuth = async () => {
    try {
      const response = await fetch("http://localhost:8080/login/is-verify", {
        method: "GET",
        headers: {token : localStorage.token},
      });

      const parseRes = await response.json();

      parseRes === true ? setIsAuthenticated(true) : setIsAuthenticated(false);

    } catch (err) {
    }
  };

  const [companies, setCompanies] = useState([]);

  const getCompanies = async() => {
    try {
      
      const response = await fetch("http://localhost:8080/companies/readall")
      const jsonData = await response.json();

      setCompanies(jsonData);
    } catch (err) {
      console.error(err.message);
    }
  }

  const [categories, setCategories] = useState([]);

  const getCategories = async() => {
    try {
      
      const response = await fetch("http://localhost:8080/categories/readall")
      const jsonData = await response.json();

      setCategories(jsonData);
    } catch (err) {
      console.error(err.message);
    }
  }

  


  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const setAuth = (boolean) =>{
    setIsAuthenticated(boolean);
  };


  useEffect(()=>{
    if("token" in localStorage)
      isAuth();
      getName();
    getCompanies();
    getCategories();
  },[isAuthenticated, logged]);

    
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home setAuth={setAuth}/>}/>
        <Route path="/login" element={!isAuthenticated? <Login setAuth={setAuth}/> : <Navigate to="/"/>}/>
        <Route path="/register" element={!isAuthenticated? <Register setAuth={setAuth}/> : <Navigate to="/login"/>}/>
        {categories.map(item=>(
          <>
          <Route key={item.categoryId} path={"/category/" + item.categoryId} element={
            <CategoryTemplate 
            setAuth={setAuth} 
            name={name} 
            logout={e => logout(e)} 
            logged={logged}
            category_id = {item.categoryId}
            category_name = {item.name}
            category_img = {item.img}
            />
          }/>
          <Route path={"/add_company/" + item.categoryId} element={
            <AddCompany 
            setAuth={setAuth}
            category_id = {item.categoryId}
            category_name = {item.name}
            />
          }/>
          </>
        ))}
        {companies.map(item=>(
          <>
          <Route path={item.route} element={
            <CompanyTemplate 
              setAuth={setAuth} 
              name={name}
              logout={e => logout(e)}
              company_id = {item.companyId}
              title={item.title}  
              logo={item.logo}
              top_img = {item.topImg}
              about = {item.about}
              schedule = {item.schedule}
              site_link = {item.siteLink}
              phone = {item.phone}
              location = {item.location}
            />
          }/>

          <Route path={"/edit_company/" + item.companyId} element={
            <EditCompany 
            setAuth={setAuth}
            category_id = {item.categoryId}
            category_name = {item.name}
            company_id = {item.companyId}
            title = {item.title}
            logo = {item.logo}
            top_img = {item.topImg}
            city = {item.city}
            location = {item.location}
            site = {item.site}
            site_link = {item.siteLink}
            schedule = {item.schedule}
            phone = {item.phone}
            route = {item.route}
            about = {item.about}
            />
          }/>

          <Route path={"/review/" + item.companyId} element={"token" in localStorage? 
            <ReviewTemplate 
            setAuth={setAuth}
            user_id = {userId}
            name={name}
            logout={e => logout(e)}
            logged={logged}
            company_id = {item.companyId}
            title = {item.title}
            /> 
            : <Navigate to="/login" />
          }/>
          </>
        ))}
      </Routes>
    </Router>
  );
}

export default App;

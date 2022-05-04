import { default as Experience } from "./experience/Experience";
import Header from "./Header/Header";
import { Affix} from 'antd';
import { Routes, Route, useNavigate } from 'react-router-dom'
import {default as Projects} from "./projects/Projects";
import {default as Certificates} from "./certificates/Certificates";
import {default as Personal} from "./personal/Personal";
import {default as Education} from "./education/Education";
import {default as Login} from "./Login/Login";
import {default as Signup} from "./Login/Signup";
import {default as Preview} from "./Preview/Preview";

import './Home.css'
 const Home=()=>{
    return(
        <div className="Body">
            
            {/* <Affix offsetTop={0}>
            <div className="Header">
            <Header/>
            </div>
            </Affix> */}
           
            <div className="Cont">
            <Routes>
            <Route path='/' element={<Login/>}/>
            <Route path='/signup' element={<Signup/>}/>
                <Route path='/personal' element={<Personal/>}/>
                <Route path='/education' element={<Education/>}/>
                <Route path='/experience' element={<Experience/>}/>
                <Route path='/certificates' element={<Certificates/>}/>
                <Route path='/projects' element={<Projects/>}/>
                <Route path='/preview' element={<Preview/>}/>
                
            </Routes>
            </div>
            
        </div>
    )
}
export default Home;
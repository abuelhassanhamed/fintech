

import { BrowserRouter as Router, Routes, Route ,Link} from "react-router-dom";

import logo from './logo.svg';
import './App.css';
import {OrderList} from './order/OrderList.js'
import {Catalog }  from './product/catalog.js';
import { HomePage } from "./home/Home.js";
import { useState } from 'react';

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';






export {MyApp}

 function MyApp(){
  const greeting="wlecome to store";

  //state variables 0 is the initial value
  const [count,setCount]=useState(0);
  

   function handleClick(){
    setCount(count+1);
   
   }


return (
<Router>
<Routes>
<Route path="/products" element={<Catalog />} />
<Route path="/orders" element={<OrderList/>} />
<Route path="/products" element={<Catalog />} />
<Route path="/" element={<HomePage  />} />
</Routes>
</Router>

);

 } 
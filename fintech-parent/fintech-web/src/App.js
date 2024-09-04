import { BrowserRouter , Routes, Route ,Link} from "react-router-dom";

import logo from './logo.svg';
import './App.css';
import {OrderList} from "./order/OrderList";
import {Catalog} from './product/catalog'
import {HomePage} from "./home/Home";

import { useState } from 'react';






export{MyApp};

function MyApp(){
  const greeting="wlecome to store";

  //state variables 0 is the initial value
  const [count,setCount]=useState(0);
  

   function handleClick(){
    setCount(count+1);
   
   }


return (
  <BrowserRouter>
<Routes>
<Route path="/productsssss" element={<Catalog />} />
<Route path="/orders" element={<OrderList/>} />
<Route path="/products" element={<Catalog />} />
<Route path="/" element={<HomePage/>} />
</Routes>
</BrowserRouter>

);

 }
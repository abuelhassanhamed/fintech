
import * as React from "react";
import * as ReactDOM from "react-dom";
import '../App.css';
import { useState } from "react";
import { useEffect } from "react";
//mport orders from '../product/data';

export {OrderList};
 
function OrderList(){
 //  const [orders,setOrders]=useState([]);
  const [orders, setOrders] = useState([{}]);

  function cancelOrder(order){
    console.log(order);
    fetch('http://localhost:8092/fintech/order/api/order',
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
            method: 'DELETE',
            body:JSON.stringify(order)
        }
    )
  
    //.then(response=>orders=response.json)
    .then(()=>{
      
      var array=[orders];
        let filteredArray =orders.filter(item => item !== order);
        if(filteredArray.length==0)
            refreshData();
          setOrders(filteredArray);
        
    })
   // .then ((data)=>{setOrders(data);});
  //.then((data)=>{orders=data;})
   // .then(console.log(orders));
   .catch(console.error);


  }

  

  function refreshData (){
    fetch('http://localhost:8092/fintech/order/api/order',
      {
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            }
      })
    //.then(response=>orders=response.json)
    .then((response)=>{return response.json();})
    .then ((data)=>{setOrders(data);});
  //.then((data)=>{orders=data;})
   // .then(console.log(orders));
   // .catch(console.error);

  }



  //let orders=[{}];
  useEffect(()=> {
  refreshData();
}, [])
    return (
       <div>
        <table>
            <thead>
            <tr>
            <th>order code</th>
            <th>total date</th>
            <th>customer id </th>
            </tr>
            </thead>
            <tbody>
       {orders.map(order =>
         
           <tr key={order.id} >
          

           
           <td  className="app-link">{order.id} </td>
           <td className="app-link">{order.orderDate} </td>
           <td className="app-link">{order.customerId} </td>
           <td>
            <button variant="contained" onClick={()=>cancelOrder(order)} >cancel order</button>
           </td>
           </tr>
           
          )}
          </tbody>
</table>
</div>
    );
}


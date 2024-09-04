import { useState } from "react";
import { useEffect } from "react";
import '../App.css';

export{Catalog};
function Catalog() {

    const [products,setProducts]=useState([{}]);

    function getCatalog() {
        fetch('http://localhost:8091/product/api/products')
        //.then(response=>orders=response.json)
        .then((response)=>{return response.json();})
        .then ((data)=>{setProducts(data);});
      //.then((data)=>{orders=data;})
       // .then(console.log(orders));
       // .catch(console.error);
    

    }

    useEffect(()=> {
        getCatalog();
      }, [])

    return (
      
       <table>
        <thead>
        <tr>
          <th>id </th>
          <th>description</th>
          <th>price </th>
          </tr>
        </thead>
        <tbody>
          {   products.map (product=>
          <tr key={product.id} >
          <td>{product.id}</td>
          <td>{product.description}</td>
          <td>{product.price}</td>
          </tr>
          )


          }

        </tbody>

       </table>
       
      
    );

}


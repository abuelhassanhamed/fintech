import * as ReactDOM from "react-dom";
import '../App.css';
import { BrowserRouter , Routes, Route ,Link} from "react-router-dom";

export {HomePage};
function HomePage(){

    return (
      <>

      <h1> Mozzo Ecommerce</h1>
      <p>track your orders</p>
      
      <p>
      <Link activeClass="active" to="products">product list</Link>
      </p>
      <p>
      <Link to="orders">order list</Link>
      </p>
      
          </>
      
    );
  
  }
  
  
  
  
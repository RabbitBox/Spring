import React from 'react';
import {Link} from "react-router-dom";
import FormSearch from "./FormSearch/FormSearch";

const Header = (props) => {
    return (


        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand" href="/">Home</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to={"/pizzas"}>Pizzas</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/ingredients"}>Ingredients</Link>
                        </li>
                    </ul>
                    <FormSearch/>
                    <form className="form-inline mt-2 mt-md-0 ml-3">
                        <Link className="btn btn-outline-info my-2 my-sm-0" to={"/login"}>Login</Link>
                    </form>

                </div>
            </nav>
        </header>


    )
}
export default Header;
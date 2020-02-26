import React, {Component} from 'react';
import './App.css';
import Header from "../Header/Header";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Ingredients from "../Ingredients/Ingredients";
import ingredientsService from "../../API/axiosIngredientService";
import IngredientDetails from "../Ingredients/ingredientDetails";
import WelcomeComponent from "../Header/FormSearch/test";


class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
        terms: [],
        name: null,
        pizzas: []
    }
  }

    componentDidMount() {
        this.loadIngredients();
    }

    loadIngredients = () => {
        ingredientsService.fetchIngredients().then(response=>{
            this.setState((prevState)=>{
                return {
                    "terms":response.data
                }
            })
        })
    }

    deleteConsultation = (i) => {
        ingredientsService.deleteIngredient(i).then(

            this.loadIngredients
        )
    }

    getDetails = (i) => {
        ingredientsService.getAllPizzasWithIngredient(i.name).then(response=>{
            console.log(response)
            this.setState((prevState)=>{
                return {
                    "name": i.name,
                    "pizzas": response.data
                }
            })
        })
    }

  render(){

    const routing = (
        <Router>

          <Header/>

            <main role="main" className="mt-3">

                <div className="container">
                    <Route path={"/ingredients"} exact render={()=>
                        <Ingredients terms={this.state.terms} onDelete={this.deleteConsultation} onDetails={this.getDetails}/>}>
                    </Route>

                    <Route path={"/ingredients/details"} exact render={()=>
                        <IngredientDetails name={this.state.name} pizzas={this.state.pizzas} />}>
                    </Route>

                    <Redirect to={"/"}/>
                </div>
            </main>



        </Router>
    )

    return (
        <div className="App">
          {routing}
          <WelcomeComponent/>
        </div>
    );
  }

}

export default App;

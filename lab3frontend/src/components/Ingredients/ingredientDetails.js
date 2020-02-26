import React from 'react';
import SinglePizza from "../SinglePizza/SinglePizza";



const IngredientDetails = (props) => {
    const singleIngredient = props.pizzas.map((p,index)=> {

        return (

            <SinglePizza  name={props.name} pizzaId={p.name} pizza={p} key={index} colClass={"col-md-6 mt-2 col-sm-12"}/>

            );

    });
    return (
        <div>
            <h1>{props.name}</h1>
            {singleIngredient}
        </div>
    )
}

export default IngredientDetails;
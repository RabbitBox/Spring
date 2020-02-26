import React from 'react';
import SingleIngredient from "../SingleIngredient/SingleIngredient";


const getIngredients = (props) => {
    const singleIngredient = props.terms.map((ingredient,index)=> {

        return (
            <SingleIngredient onDetails={props.onDetails} onDelete={props.onDelete} ingredientId={ingredient.name} ingredient={ingredient} key={index} colClass={"col-md-6 mt-2 col-sm-12"}/>
        );

    });
    return (
        <div className={"row"}>
            <div className="row">
                <h4 className="text-upper text-left">Ingredients</h4>
                <div className="table-responsive">
                    <table className="table tr-history table-striped small">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Spicy</th>
                            <th scope="col">Veggie</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                            {singleIngredient}
                        </tbody>
                    </table>
                </div>
                <button className="btn btn-outline-secondary">
                    <span><strong>Add new ingredient</strong></span>
                </button>
            </div>
        </div>
    )
}

export default getIngredients;
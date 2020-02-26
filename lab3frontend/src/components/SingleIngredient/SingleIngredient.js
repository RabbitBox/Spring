import React, {Component} from 'react';
import Moment from "react-moment";
import {Link} from "react-router-dom";


class SingleIngredient extends Component {

    render() {

        return (
            <tr>
                <td scope="col">{this.props.ingredient.name}</td>
                <td scope="col">{this.props.ingredient.amount}</td>
                <td scope="col">{this.props.ingredient.spicy}</td>
                <td scope="col">{this.props.ingredient.veggie}</td>
                <td scope="col">
                    <button className="btn btn-warning">
                        <span className="fa fa-edit"/>
                        <span><strong>Edit</strong></span>
                    </button>
                    <a onClick={()=>this.props.onDelete(this.props.ingredient.name)} className="btn btn-danger" title="Remove">
                        <i className="fa fa-trash"></i>
                    </a>
                    <button className="nav-link" className="btn btn-success">
                    <Link onClick={()=>this.props.onDetails(this.props.ingredient)}  to={"/ingredients/details"} >Details</Link>
                    </button>

                </td>
            </tr>
        );
    }
}


export default SingleIngredient;
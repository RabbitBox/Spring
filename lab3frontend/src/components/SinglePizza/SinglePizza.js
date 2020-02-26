import React, {Component} from 'react';
import Moment from "react-moment";
import {Link} from "react-router-dom";


class SingleIngredient extends Component {

    render() {

        return (
            <div>
            <tr>
                <td scope="col">{this.props.pizzaId}</td>
            </tr>
            </div>
        );
    }
}


export default SingleIngredient;
import axios from "./customAxios"
import qs from 'qs'

const ingredientService = {
    fetchIngredients: ()=> {
        return axios.get("ingredients/all");
    },
    deleteIngredient: (name)=> {
        return axios.delete("ingredients/" + name);
    },
    getAllPizzasWithIngredient: (name)=> {
        return axios.get("ingredients/" + name + "/pizzas");
    }

}

export default ingredientService;
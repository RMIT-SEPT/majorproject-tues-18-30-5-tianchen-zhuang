import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/rest/users/all';

class userInfo {

    getUsers(){
        // return fetch(USERS_REST_API_URL).then(response => response.json())
        // .then(data=> this.setState({
        //     users:data,
        //     isLoading: false,
        // }))
        // .catch(error =>this.setState({error, isLoading:false}));
        
        return axios.get(USERS_REST_API_URL);
    }
}

export default new userInfo();
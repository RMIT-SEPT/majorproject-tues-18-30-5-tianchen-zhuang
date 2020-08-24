import React from 'react';
import userInfo from '../services/userInfo';

class authenticate extends React.Component {

constructor(props){
    super(props)
    this.state = {
        users:[]
    }
}

componentDidMount(){
    userInfo.getUsers().then((response) => {
        this.setState({ users: response.data})
    });
}

}


checkPass(username, password){
        


}


export default getUserInfo
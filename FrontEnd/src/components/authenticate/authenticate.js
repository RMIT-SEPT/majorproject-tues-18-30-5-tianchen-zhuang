import axios from 'axios'
import React from 'react';

const info = '';
const userinfo = 'http://localhost:8080/api/user/email/';

    class Authenticate extends React.Component {
     
            
            constructor(props){
                super(props)
                this.state = {
                    users:[]
                }
            }
       


    checkPwd(username, password){
        
        // username = 'test@test.com';
        // console.log('uname',username);
        // console.log('uino',userinfo+username);
        let info = axios.get(userinfo+username);
        let globalinfo = this.state.users;
        let check = false;

        info.then((response) => {   
            console.log(response.data.password)
            if(response.data.password == password){
                console.log("yes",check);
                check = true;
                return true;
            }else{
                console.log('no',check);
                check = false;
                return false;
            }
            // info = response.data;
            // this.setState({ users: globalinfo})
            // this.setState({ users: response.data})
        });

        setTimeout(() => {      
        // if(check == true){
        //     return true;
        // }else{
        //     return false;
        // }
        
        console.log('home',check);
        }, 2000);
        // console.log('home',check);
        // if(check == true){
        //     return true;
        // }else{
        //     return false;
        // }


    }

}


export default new Authenticate
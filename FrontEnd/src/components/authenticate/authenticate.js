import axios from 'axios'
import React from 'react';

const info = '';
const userinfo = 'http://localhost:8080/api/user/email/';

    class Authenticate extends React.Component {
     
            
            constructor(props){
                super(props)
                this.state = {
                    users:[],
                    pass:""
                }
            }
       
        async getApi(api){
            return  axios.get(userinfo+api);
            // let check = false;
            // let password = "";
            // await info.then((response) => { 
            //     console.log(response.data.password)
            //     this.setState({
            //         pass: response.data.password
            //       });
            //       console.log("passss",this.state.pass)
            // });
        }

    //      checkPwd(username, password){
    //          this.getApi(username);
    //          console.log('api ',this.state.pass);


    // }

}


export default new Authenticate
import axios from 'axios'
import React from 'react';

const info = '';
const userinfo = 'http://localhost:8080/api/user/email/';
const loginRegApi = 'http://localhost:8080/api/business/';
const businessInfo = 'http://localhost:8080/api/business/email/';
const businessList='http://localhost:8080/api/business/all/'
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
        async loginAsbusiness(api){
            return  axios.get(businessInfo+api);

        }
        async registerAsBusiness(businessInfo){
            console.log(businessInfo['city'], "city");
            return await axios.post(loginRegApi,{
                "username": businessInfo['username'],
                "password": businessInfo['password'],
                "email": businessInfo['email'],
                "businessName": businessInfo['business_name'],
                "country": businessInfo['country'],
                "city": businessInfo['city'],
                "street": businessInfo['street'],
                "postCode": businessInfo['postcode']
            });
            
        }
        async getBusinessList(){
            return axios.get(businessList);
        }


}


export default new Authenticate
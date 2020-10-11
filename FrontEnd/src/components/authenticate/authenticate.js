import axios from 'axios'
import React from 'react';

const info = '';
const userinfo = 'http://localhost:8080/api/customer/email/';
const userRegApi = 'http://localhost:8080/api/customer/';
const loginRegApi = 'http://localhost:8080/api/business/';
const businessInfo = 'http://localhost:8080/api/business/email/';
const businessList='http://localhost:8080/api/business/all/';
const deleteBusiness = 'http://localhost:8080/api/business/';
const getEventById = 'http://localhost:8080/api/event/business/';
const deleteEvent  = 'http://localhost:8080/api/event/';
const addEvent  = 'http://localhost:8080/api/event';
const checkEvent  = 'http://localhost:8080/api/event/';

const makeBooking = 'http://localhost:8080/api/booking/';
const allBooking = 'http://localhost:8080/api/booking/all/';
const getBookingByCustomerId  = 'http://localhost:8080/api/booking/customer/';
const deleteBooking  = 'http://localhost:8080/api/booking/';
const customerList = 'http://localhost:8080/api/customer/all/';
const deleteCustomer = 'http://localhost:8080/api/customer/';


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
        async registerAsUser(userinfo)
        {
            return await axios.post(userRegApi,{
                "username": userinfo['username'],
                "password": userinfo['password'],
                "email": userinfo['email']
            });
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

        async deleteBooking(id)
        {
            return axios.delete(deleteBooking+id);
        }

        getEventByBusinessId(id){
            return  axios.get(getEventById+id);
        }
        deleteEvent(id){
            return axios.delete(deleteEvent+id);
        }
        addEvent(info,id,date){
            return axios.post(addEvent,{
                "businessId" :id,
                "created" : date,
                "eventDate":info['eventDate'],
                "eventDesc":info['eventDesc'],
                "eventName" : info['EventName'],
                "eventTime":info['eventTime'],
                "maxCapacity":info['maxCapacity']
            })
        }

        makeBooking(customerid,eventId)
        {
            return axios.post(makeBooking,{
                "customerId": customerid,
                "eventId": eventId
            })
        }
        
        getBookingByCustomerId(customerId){
            return axios.get(getBookingByCustomerId+customerId);
        }
        async getCustomerList(){
            return axios.get(customerList);
        }

        deleteBusiness(id){
            return axios.delete(deleteBusiness+id);
        }
        getEventById(id)
        {
            return axios.get(checkEvent+id);
        }

        deleteCustomer(id){
            return axios.delete(deleteCustomer+id);
        }
}


export default new Authenticate
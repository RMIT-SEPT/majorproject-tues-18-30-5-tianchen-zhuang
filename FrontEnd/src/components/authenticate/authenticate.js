import axios from 'axios'
import React from 'react';

//if running locally please change awsUrl references to local
const awsUrl = 'http://tues18305live-env.eba-hsi7bj9f.us-east-1.elasticbeanstalk.com';
const local = 'http://localhost:5000'

const userinfo = awsUrl+'/api/customer/email/';
const userRegApi = awsUrl +'/api/customer/';
const loginRegApi = awsUrl + '/api/business/';
const businessInfo = awsUrl+ '/api/business/email/';
const businessList= awsUrl + '/api/business/all/';
const deleteBusiness = awsUrl+ '/api/business/';
const getEventById = awsUrl+ '/api/event/business/';
const deleteEvent  = awsUrl + '/api/event/';
const addEvent  = awsUrl + '/api/event';
const checkEvent  = awsUrl + '/api/event/';

const makeBooking = awsUrl + '/api/booking/';
const allBooking = awsUrl + '/api/booking/all/';
const getBookingByCustomerId  = awsUrl + '/api/booking/customer/';
const deleteBooking  = awsUrl + '/api/booking/';
const customerList = awsUrl + '/api/customer/all/';
const deleteCustomer = awsUrl + '/api/customer/';



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

        async editCustomer(customerId, userinfo){
            
            return await axios.put(userRegApi+customerId,{
                "username": userinfo['username'],
                "password": userinfo['password'],
                "email": userinfo['email']
            });
            
        }

        async editBusiness(businessId, businessInfo){
            
            return await axios.put(loginRegApi+businessId,{
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
}


export default new Authenticate
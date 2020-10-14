import React, { Component } from 'react'
import ClassCard from './ClassCard'

// import {_getClassList} from '../../api/class'
import authenticate from '../authenticate/authenticate';
import './booking.css'

export default class businessList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: [],
            checkstatus: 0
        };
        this.getClassList = this.getClassList.bind(this)
    }

    showDetail(id) {
        console.log("show detail", id);
        sessionStorage.setItem('businessId', id);
        this.props.history.push(`/booking/${id}`);
    }

    componentDidMount() {
        if(this.state.checkstatus == 1){
        this.getClassList();
        }else{
            let res = authenticate.getBusinessList();
        
            res.then((response) => {
             
                console.log(response.data ,"resp")
                this.setState({
                    list:response.data
                })
                    
            });
            console.log(sessionStorage.getItem('user'));
            
        }
    }

    async getClassList() {
        let res = await authenticate.getBusinessList();
        
        res.then((response) => {
         
            console.log(response ,"resp")
            
                
        });
        
    }

    render() {
        let classList = [];
        
        for (let i = 0; i<this.state.list.length; i++) {
           
            var bname = this.state.list[i].businessName;
            var bid = this.state.list[i].businessId;
            var desc = this.state.list[i].username;
          
            classList.push(<ClassCard showDetail={this.showDetail.bind(this, bid)} title={bname} id={bid} description={desc}  />)
            
        }
        return (
            <div>
                <h1>Business list</h1>
        <label>Welcome {sessionStorage.getItem('username')}</label>
                <div className="classList">
                    {classList}
                </div>
            </div>
        )
    }
}

import React from 'react';
import { Card } from 'antd';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import authenticate from '../authenticate/authenticate';
import SchedleCard from './scheduleCard'
class booking extends React.Component{
    constructor(probs) {
        super(probs);
       
        this.state = {
            startDate : new Date(),
            list: []
        };
        // this.handleChange = this.handleChange.bind(this);
        // this.handleSubmit = this.handleSubmit.bind(this);
      }

    componentWillMount() {
        console.log(sessionStorage.getItem('businessId') + "business iD ")
        let res = authenticate.getEventByBusinessId(sessionStorage.getItem('businessId'));
        res.then((response)=>{
          this.setState({
            list:response.data
            
        })
        console.log(response.data)
        })
    }

     handleColor = time => {
        return time.getHours() > 12 ? "text-success" : "text-error";
      };
    
    setStartDate(e){
        let startDate = this.state.startDate;
        startDate = e;
        this.setState({
            startDate
        });
        console.log(startDate);
        console.log(e);
        
    }
    // setStartDate =  new Date();


    getData(){
    
    let d = localStorage.getItem('userInfo');
    var str = String(this.state.startDate);
    var res = str.split(' ');
    console.log(res);
    alert("your booking time for XX is " + res[0]+res[1]+res[3]+res[4]);
}

  render() {
    
    const scheduleList = [];
    for (let i = 0; i<this.state.list.length; i++) {
      if(this.state.list[i].currCapacity <this.state.list[i].maxCapacity ){
          
        scheduleList.push(<SchedleCard 
          eventName ={this.state.list[i].eventName}
          maxCapacity={this.state.list[i].maxCapacity}
          currCapacity = {this.state.list[i].currCapacity}
          eventDate={this.state.list[i].eventDate}
          eventTime={this.state.list[i].eventTime}
           />)
           console.log(this.state.list[i].eventDate + "consolss")
      }
      // console.log(items+ " items")
    }
    return (

        <div>
             <h1>welcome {sessionStorage.getItem('username')}</h1>
             <div>
                <table class="table table-bordered">
                <thead>
                  <tr>
                    <th scope="col">Select attendance</th>
                    <th scope="col">Event Name</th>
                    <th scope="col">Curr Capacity</th>
                    <th scope="col">Max Capacity</th>
                    <th scope="col">Event Date</th>
                    <th scope="col">Event Time</th>
                    <th scope="col">Selection</th>
                  </tr>
                </thead>
                <tbody>
                  
                  {
                      scheduleList
                  }
                  
                
                </tbody>
              </table>

             </div>
             {/* <DatePicker
                    showTimeSelect
                    selected={this.state.startDate}
                    onChange={date => this.setStartDate(date)}
                    timeClassName={this.handleColor}
                    />
         <button onClick={()=>this.getData()}>booking</button> */}


        </div>

       
    );
}

}

export default booking


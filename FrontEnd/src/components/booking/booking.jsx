import React from 'react';
import { Card } from 'antd';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useState } from 'react';
const { Meta } = Card;


class booking extends React.Component{
    constructor(probs) {
        super(probs);
       
        this.state = {
            startDate : new Date(),
        //   input: {}
        };
        // this.handleChange = this.handleChange.bind(this);
        // this.handleSubmit = this.handleSubmit.bind(this);
      }
    //   handleChange(event) {
    //     let input = this.state.input;
    //     input['date'] = event.target.value;
      
    //     this.setState({
    //       input
    //     });
    //     console.log(input['date']);
    //   }
   

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
      
    return (
       
        <div>
             <h1>welcome {sessionStorage.getItem('username')}</h1>
             <DatePicker
                    showTimeSelect
                    selected={this.state.startDate}
                    onChange={date => this.setStartDate(date)}
                    timeClassName={this.handleColor}
                    />
         <button onClick={()=>this.getData()}>booking</button>

        </div>

       
    );
}

}

export default booking


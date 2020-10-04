import React from 'react';
import { Card } from 'antd';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import authenticate from '../authenticate/authenticate';
import SchedleCard from './scheduleCard'
class businessEvent extends React.Component{
    constructor(probs) {
        super(probs);
       
        this.state = {
            startDate : new Date(),
            list: [],
            input:{}
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }

    componentWillMount() {
        console.log(sessionStorage.getItem('businessId') + "business iD ")
        let res = authenticate.getEventByBusinessId(sessionStorage.getItem('bid'));
        res.then((response)=>{
          this.setState({
            list:response.data
            
        })
        
        })
        
    }
    async handleSubmit(event) {
      // let history = useHistory();
    
      event.preventDefault();
    
      // if(this.validate()){
        
          console.log(this.state);
          let input = {};
          input["EventName"] = "";
          input["EventDes"] = "";
          input["eventDate"] = "";
          input["eventTime"] = "";
          input["maxCapacity"] = "";

          this.setState({input:input});
          authenticate.addEvent(this.state.input,sessionStorage.getItem('businessId'),this.getDate())
          window.location.reload(false);
          

    }
    getDate(){
      var today = new Date();
      var dd = today.getDate();

        var mm = today.getMonth()+1; 
        var yyyy = today.getFullYear();
        if(dd<10) 
        {
            dd='0'+dd;
        } 

        if(mm<10) 
        {
            mm='0'+mm;
        } 
        today = mm+'-'+dd+'-'+yyyy;
        return today;

      
    }
    delete(id){
     authenticate.deleteEvent(id);
     window.location.reload(false);
      console.log('Deleted id =  ' + id)
    }
    edit(binfo){
      console.log('This is id edit' + binfo)
    }
    // setStartDate =  new Date();


//     getData(){
    
//     let d = localStorage.getItem('userInfo');
//     var str = String(this.state.startDate);
//     var res = str.split(' ');
//     console.log(res);
//     alert("your booking time for XX is " + res[0]+res[1]+res[3]+res[4]);
// }
handleChange(event) {
  let input = this.state.input;
  input[event.target.name] = event.target.value;

  this.setState({
    input
  });
}
  render() {
    
    const scheduleList = [];
    for (let i = 0; i<this.state.list.length; i++) {
      if(this.state.list[i].currCapacity <this.state.list[i].maxCapacity ){
          
        scheduleList.push(<SchedleCard 
          eventId ={this.state.list[i].eventId}
          eventName ={this.state.list[i].eventName}
          maxCapacity={this.state.list[i].maxCapacity}
          currCapacity = {this.state.list[i].currCapacity}
          eventDate={this.state.list[i].eventDate}
          eventTime={this.state.list[i].eventTime}
          edit={this.edit.bind(this, this.state.list[i])}
          delete ={this.delete.bind(this, this.state.list[i].eventId)}
           />)
      
      }
      // console.log(items+ " items")
    }
   
    return (

        <div>
              
             <h1>welcome {sessionStorage.getItem('bname')}</h1>

             <div>
                <table class="table table-bordered">
                <thead>
                  <tr>
                    <th scope="col">Event ID</th>
                    <th scope="col">Event Name</th>
                    <th scope="col">Current Capacity</th>
                    <th scope="col">Maximum Capacity</th>
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
             <div class="card">
              <div class="card-header">
                <h3>Add An Event</h3>
                
              </div>
              <div class="card-body">
              <form onSubmit={this.handleSubmit}>
                  <div class="form-group">
                    <label for="EventName">Event Name</label>
                    <input type="text" class="form-control" onChange={this.handleChange} value = {this.state.input.EventName} name="EventName" aria-describedby="EventName" placeholder="Enter Event Name"/>
                  </div>
                  <div class="form-group">
                    <label for="EventDes">Event Description</label>
                    <input type="text" class="form-control" onChange={this.handleChange} value = {this.state.input.EventDes} name="EventDes" placeholder="Enter Description"/>
                  </div>
                  <div class="form-group">
                    <label for="eventDate">Event Date</label>
                    <input type="text" class="form-control" onChange={this.handleChange} value = {this.state.input.eventDate}  name="eventDate" placeholder="please enter in a format of DD-MM-YYYY"/>
                  </div>
                  <div class="form-group">
                    <label for="eventTime">Event Time</label>
                    <input type="text" class="form-control"onChange={this.handleChange}   value = {this.state.input.eventTime} name="eventTime" placeholder="Please enter in a format of HH:MM:SS"/>
                  </div>
                  <div class="form-group">
                    <label for="maxCapacity">Maximum Capacity</label>
                    <input type="text" class="form-control"  onChange={this.handleChange} value = {this.state.input.maxCapacity} name="maxCapacity" placeholder="Please enter maximum capacity"/>
                  </div>
                  
                  <input type="submit"  value= "Add to event"class="btn btn-primary"/>
                </form>
              </div>
            </div>

        </div>

       
    );
}

}

export default businessEvent


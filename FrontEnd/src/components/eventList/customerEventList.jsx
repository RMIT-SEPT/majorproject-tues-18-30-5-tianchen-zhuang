import React,{ Component } from 'react';
import authenticate from '../authenticate/authenticate';
import {Link} from 'react-router-dom';

class customerEventList extends Component
{
    constructor(props) {
        super(props);
       
        this.state = {
          booking: [],
        };
      }

      componentDidMount() {
        console.log(sessionStorage.getItem('customerId') + "user iD ");
        let res = authenticate.getBookingByCustomerId(sessionStorage.getItem('customerId'));
        console.log(res);
          res.then((response)=>{
            this.setState({
              booking: response.data
            })}).catch((error) =>
            {
              console.error(error);
            })
      console.log(this.state.booking);
    }

    HandleDelete = (bookingId) => {
      authenticate.deleteBooking(bookingId);
      window.location.reload(false);
    }

      render(){
        const bookingShowingCard = [];
        console.log(this.state.booking.length);
        for(let i=0;i<this.state.booking.length;i++)
        {
          console.log(i);
          console.log(this.state.booking[i]);
          if(this.state.booking[i] != null)
        {
          console.log(this.state.booking[i]);
          const button = (<button className="btn btn-danger" onClick={() => this.HandleDelete(this.state.booking[i].bookingId)} value="Delete" name="delete">Delete</button>);
          bookingShowingCard.push(      
            <tr>
              <td scope="col">{this.state.booking[i].bookingId}</td>
              <td scope="col">{this.state.booking[i].customerId}</td>
              <td scope="col"><Link to={{
                pathname: './customerBookingList',
                data: this.state.booking[i].eventId
              }}>{this.state.booking[i].eventId} You can click to have a check</Link></td>
              <td scope="col" >
              {button}
              </td>
            </tr>
          )
          console.log(this.state.booking[i].bookingId);
      }
    }
          
          // console.log(items+ " items")
        
        return (
    
            <div>
                 <h1>welcome {sessionStorage.getItem('username')}, here is your bookings</h1>
                 <div>
                    <table className="table table-bordered">
                    <thead>
                  <tr>
                  <th scope="col">Booking ID</th>
                    <th scope="col">Customer ID</th>
                    <th scope="col">Event ID</th>
                    <th scope="col">Delete</th>
                  </tr>
                </thead>
                    <tbody>
                      {
                        bookingShowingCard
                      }
                    
                    </tbody>
                  </table>
    
                 </div>
            </div>
    
           
        );
      }
    }

export default customerEventList;
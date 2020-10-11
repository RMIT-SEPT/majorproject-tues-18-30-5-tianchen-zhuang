import React,{ Component } from 'react';
import authenticate from '../authenticate/authenticate';
import EventShowingCard from './eventShowingCard';
class customerBookingList extends Component
{
    constructor(props) {
        super(props);
        const { data } = this.props.location;
        let temEvent = authenticate.getEventById(data);
        console.log(data);
        console.log(temEvent);
        this.state = {
          temEvent,
          currentEvent:[]
        };
      }

      componentDidMount() {
        let temEvent = this.state.temEvent;
        console.log(temEvent);
        console.log(this.state.currentEvent.length != 0);
          temEvent.then((response)=>{
            this.setState({
              currentEvent: response.data
            })
            console.log(response.data);
            console.log(this.state.currentEvent);
          }).catch((error) =>
            {
              console.error(error);
            })
      console.log(this.state.currentEvent);
    }

    render()
    {

        const eventShowingCard = [];
        console.log(this.state.currentEvent);
        console.log(this.state.currentEvent.length);
        console.log(this.state.currentEvent.eventId);
        if(this.state.currentEvent.length != 0)
        {
            eventShowingCard.push(  <EventShowingCard    
                eventId ={this.state.currentEvent.eventId}
              eventName ={this.state.currentEvent.eventName}
              eventDate={this.state.currentEvent.eventDate}
              eventTime={this.state.currentEvent.eventTime}
              />);
        }
        console.log(this.state.currentEvent[0]);


        return(
            <div>
              
             <h1>Here is your booking for this event</h1>

             <div>
                <table class="table table-bordered">
                <thead>
                  <tr>
                    <th scope="col">Event ID</th>
                    <th scope="col">Event Name</th>
                    <th scope="col">Event Date</th>
                    <th scope="col">Event Time</th>
                  </tr>
                </thead>
                <tbody>
                {
                    eventShowingCard
                }
                </tbody>
              </table>

             </div>
            </div>
        )
    }
}
export default customerBookingList;
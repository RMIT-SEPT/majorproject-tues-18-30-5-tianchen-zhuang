import React from 'react';
import { Link  } from 'react-router-dom';
import { withRouter } from "react-router-dom";
import { useHistory } from "react-router-dom";
import { Redirect } from 'react-router';
import history from '../tools/history';
import './style.css'

class SelectUser extends React.Component{

    constuctor() {
        this.routeChange = this.routeChange.bind(this);
      }
    
      routeChange() {
        let path = '/login/user';
        this.props.history.push(path);
      }
    render() {
        return (
            <div className="select_user">
            <h2>Select login type</h2> 
            <div>
                <button className ="select_button"  onClick= {event =>  window.location.href='/login/user'} >Login as User</button>
                <button className ="select_button"onClick= {event =>  window.location.href='/login/business'}>Login as Business</button>
            </div>
            </div>

        )
    }

    // <li><Link className="nav-link" to="/login">Login</Link></li>


}
export default SelectUser
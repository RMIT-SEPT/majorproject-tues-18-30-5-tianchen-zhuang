import { getKeyThenIncreaseKey } from 'antd/lib/message';
import React from 'react';
import { Link } from 'react-router-dom';
import { browserHistory } from 'react-router'
import businessList from '../booking/businessList';

class NavBar extends React.Component{

    // changeToLogout()
    // {
    //     if(localStorage.getItem() != null)
    //     {
    //         document.getElementsByClassName("login")[0].getElementsByTagName('li')[0].innerHTML="Log out";
    //     }
    // }
    UserLogout = () =>
    {
        console.log(sessionStorage.getItem('username'));
        sessionStorage.removeItem('username');
        window.location.reload(false);
    }
    BusinessLogout = () =>
    {
        console.log(sessionStorage.getItem('bname'));
        sessionStorage.removeItem('bname');
        window.location.reload(false);
    }
    render() {
        let userIsLogin;
        let businessIsLogin;
        console.log(sessionStorage);
        if(sessionStorage.getItem('username') != null)
            {
                userIsLogin = <li onClick = {this.UserLogout}><Link className="nav-link" to="/">User Log out</Link></li>
            }
            else
            {
                userIsLogin = <li><Link className="nav-link" to="/login">User Login</Link></li>
            }
        if(sessionStorage.getItem('bname') != null)
            {
                businessIsLogin = <li onClick = {this.BusinessLogout}><Link className="nav-link" to="/">Business Log out</Link></li>
            }
            else
            {
                businessIsLogin = <li><Link className="nav-link" to="/login">Business Login</Link></li>
            }
        return (
            <header className = 'navBar'>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                <div><a href="https://www.rmit.edu.au/" className="navbar-brand">RMIT</a></div>
                    <ul className="navbar-nav">
                    {userIsLogin}
                    {businessIsLogin}
                    <li><Link className="nav-link" to="/register">Register</Link></li>
                    <li><Link className="nav-link" to="/bookinglist">Booking List</Link></li>
                    <li><Link className="nav-link" to="/bookingEvent">Business Event List</Link></li>
                    <li><Link className="nav-link" to="/myEventList">My Booking List</Link></li>
                    </ul>

                </nav>
               
            </header>
        )
    }


}

export default NavBar
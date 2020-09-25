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
    Logout = () =>
    {
        console.log(localStorage.getItem('userInfo'));
        localStorage.removeItem('userInfo');
    }
    render() {
        let isLogin;
        if(localStorage.getItem('userInfo') != null)
                    {
                        isLogin = <li onClick = {this.Logout}><Link className="nav-link" to="/login">Log out</Link></li>
                    }
                    else
                    {
                        isLogin = <li><Link className="nav-link" to="/login">Login</Link></li>
                    }
        return (
            <header className = 'navBar'>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                <div><a href="https://www.rmit.edu.au/" className="navbar-brand">RMIT</a></div>
                    <ul className="navbar-nav">
                    {isLogin}
                    <li><Link className="nav-link" to="/register">Register</Link></li>
                    </ul>

                </nav>
               
            </header>
        )
    }


}

export default NavBar
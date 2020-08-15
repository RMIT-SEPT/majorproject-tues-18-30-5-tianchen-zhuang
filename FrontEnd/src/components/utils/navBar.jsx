import React from 'react';
import { Link } from 'react-router-dom';

class NavBar extends React.Component{
    render() {
        return (
            <header className = 'navBar'>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                <div><a href="https://www.rmit.edu.au/" className="navbar-brand">RMIT</a></div>
                    <ul className="navbar-nav">
                      <li><Link className="nav-link" to="/login">Login</Link></li>
                    <li><Link className="nav-link" to="">Register</Link></li>
                    </ul>

                </nav>
               
            </header>
        )
    }


}

export default NavBar
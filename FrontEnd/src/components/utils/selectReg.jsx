import React from 'react';

import './style.css'

class SelectReg extends React.Component{

    render() {
        return (
            <div className="select_user">
            <h2>Select register type</h2> 
            <div>
                <button className ="select_button"  onClick= {event =>  window.location.href='/register/user'} >Register as User</button>
                <button className ="select_button"onClick= {event =>  window.location.href='/register/business'}>Register as Business</button>
            </div>
            </div>

        )
    }

    // <li><Link className="nav-link" to="/login">Login</Link></li>


}
export default SelectReg
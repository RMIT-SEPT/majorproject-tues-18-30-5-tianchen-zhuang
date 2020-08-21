import React from 'react';
import { Menu } from 'antd';
import { Card } from 'antd';
import './register.css'
import { Input } from 'antd';

class Register extends React.Component{
  render() {
    return (
        <div className="register">
            <Card >
                <Menu mode="horizontal">
                    <Menu.Item><h3>Register</h3></Menu.Item>
                </Menu>
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
                    <div className="register-input-row"> 
                    <label>Email:</label>
                        <Input placeholder="Enter your email" name="email"  />
                    </div>
                    <div className="register-input-row">
                    <label>Password :</label>
                        <Input.Password placeholder="Enter your password " name="password" />
                    </div>
                    <div className="register-input-row">
                    <label>First Name:</label>
                        <Input placeholder="Enter your first name" name="fname" />
                    </div>
                    <div className="register-input-row">
                    <label>User name:</label>
                        <Input placeholder="Enter your last name" name="lname" />
                    </div>
                    <div className = "register-select">
                    <label for="type">register as: </label>
                    <select className="register-type" name="register-type" id="user">
                    <option value="user">user</option>
                    <option value="business">business</option>
                    </select>
                        </div> 

                    <button className="btn btn-success" >Login</button>
            </Card>
        </div>


    )
}
}

export default Register


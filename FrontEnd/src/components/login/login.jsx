import React from 'react';
import { Menu } from 'antd';
import { Card } from 'antd';
import './login.css'
import { Input } from 'antd';

class Login extends React.Component{
  render() {
    return (
        <div className="login">
            <Card >
                <Menu mode="horizontal">
                    <Menu.Item>Login</Menu.Item>
                </Menu>
                    {/*<ShowInvalidCredentials hasLoginFailed={this.state.hasLoginFailed}/>*/}
                    {/*<ShowLoginSuccessMessage showSuccessMessage={this.state.showSuccessMessage}/>*/}
                    <div className="login-input-row">
                        <Input placeholder="User Name" name="username"  />
                    </div>
                    <div className="login-input-row">
                        <Input.Password placeholder="Password" name="password" />
                    </div>
                    <button className="btn btn-success" >Login</button>
            </Card>
        </div>


    )
}
}

export default Login


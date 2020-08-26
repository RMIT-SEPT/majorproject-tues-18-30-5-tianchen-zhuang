import React from 'react';
import { Menu } from 'antd';
import { Card } from 'antd';
import './register.css'
import { Input } from 'antd';


class Register extends React.Component{
    constructor() {
        super();
        this.state = {
          input: {},
          errors: {}
        };
         
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
         
      handleChange(event) {
        let input = this.state.input;
        input[event.target.name] = event.target.value;
      
        this.setState({
          input
        });
      }
        
      handleSubmit(event) {
        event.preventDefault();
      
        if(this.validate()){
            console.log(this.state);
      
            let input = {};
            input["password"] = "";
            input["email"] = "";
            input["username"] = "";
            input["cPassword"] = "";
            this.setState({input:input});
      
            alert('Thank you for register');
        }
      }
      
      validate(){
          let input = this.state.input;
          let errors = {};
          let isValid = true;
      
      
          if (!input["email"]) {
            isValid = false;
            errors["email"] = "Please enter your email Address.";
          }
      
          if (typeof input["email"] !== "undefined") {
              
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(input["email"])) {
              isValid = false;
              errors["email"] = "Please enter valid email address.";
            }
          }
      
          if (!input["password"]) {
            isValid = false;
            errors["password"] = "Please enter your password.";
          }
          if (typeof input["password"] !== "undefined") {
              
            var pattern = new RegExp(/^[a-zA-Z0-9]{6,}$/i);
            if (!pattern.test(input["password"])) {
              isValid = false;
              errors["password"] = "Please enter password more than 6 characters";
            }
          }
          if (!input["username"]) {
            isValid = false;
            errors["username"] = "Please enter your uername.";
          }
          if (!input["cPassword"]) {
            isValid = false;
            errors["cPassword"] = "Please enter confirm your password.";
          }
          if (input["cPassword"]!=input["password"]) {
            isValid = false;
            errors["password"] = "password and confirm password doesnt match";
          }
      
          this.setState({
            errors: errors
          });
      
          return isValid;
      }
  render() {
    return (
        
        <div>
        <h2>Register as user</h2>
        <form onSubmit={this.handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          name="email"
          type="text"
          id="email"
          placeholder="Enter your email"
          value={this.state.input.email}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.email}</div>
        <label htmlFor="email">User Name</label>
        <input
          name="username"
          type="username"
          id ="username"
          placeholder="Enter your username"
          value={this.state.input.username}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.username}</div>
        <label htmlFor="password">Password</label>
        <input
          name="password"
          type="password"
          id ="password"
          placeholder="Enter your password"
          value={this.state.input.password}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.password}</div>
        <label htmlFor="email">confirm password</label>
        <input
          name="cPassword"
          type="password"
          id ="cPassword"
          placeholder="Please confirm your password"
          value={this.state.input.cPassword}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.cPassword}</div>
        <button className= "reg_but"type="submit">Register</button>
        <button className="reg_but" type="button" onClick= {event =>  window.location.href='/register'} >Back </button>
        
      </form>
      </div>


    )
}
}

export default Register


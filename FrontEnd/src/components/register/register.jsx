import React from 'react';
import './register.css'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createUser } from '../utils/addUser'; 

class Register extends React.Component{
    constructor() {
        super();
        this.state = {
          username: "",
          password: "",
          cPassword:"",
          email: "",
          roleID: "",
          errors: {}
        };
         
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
         
      handleChange(e) {
        this.setState({[e.target.name]: e.target.value});
      }
        
      handleSubmit(event) {
        event.preventDefault();

        let input = this.state.input;
      
        if(this.validate()){
            console.log(this.state);

            const newUser = {
              username: this.state.username,
              password: this.state.password,
              email: this.state.email,
              roleID: this.state.roleID 
          }
  
          this.props.createUser(newUser, this.props.history)
      
            alert('Thank you for register');
        }
      }

      
      validate(){
          let state = this.state;
          let errors = {};
          let isValid = true;
      
      
          if (!state.email) {
            isValid = false;
            errors["email"] = "Please enter your email Address.";
          }
      
          if (typeof state.email !== "undefined") {
              
            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(state.email)) {
              isValid = false;
              errors["email"] = "Please enter valid email address.";
            }
          }
      
          if (!state.password) {
            isValid = false;
            errors["password"] = "Please enter your password.";
          }
          if (typeof state.password !== "undefined") {
              
            var pattern = new RegExp(/^[a-zA-Z0-9]{6,}$/i);
            if (!pattern.test(state.password)) {
              isValid = false;
              errors["password"] = "Please enter password more than 6 characters";
            }
          }
          if (!state.username) {
            isValid = false;
            errors["username"] = "Please enter your uername.";
          }
          if (!state.cPassword) {
            isValid = false;
            errors["cPassword"] = "Please enter confirm your password.";
          }
          if (state.cPassword!=state.password) {
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
          value={this.state.email}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.email}</div>
        <label htmlFor="email">User Name</label>
        <input
          name="username"
          type="username"
          id ="username"
          placeholder="Enter your username"
          value={this.state.username}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.username}</div>
        <label htmlFor="password">Password</label>
        <input
          name="password"
          type="password"
          id ="password"
          placeholder="Enter your password"
          value={this.state.password}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.password}</div>
        <label htmlFor="email">confirm password</label>
        <input
          name="cPassword"
          type="password"
          id ="cPassword"
          placeholder="Please confirm your password"
          value={this.state.cPassword}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.cPassword}</div>
        <button className= "reg_but" type="submit">Register</button>
        <button className="reg_but" type="button" onClick= {event =>  window.location.href='/register'} >Back </button>
        
      </form>
      </div>


    )
}
}
Register.propTypes = {
  createProject: PropTypes.func.isRequired
};
export default connect(
  null,
  { createUser }
)(Register);


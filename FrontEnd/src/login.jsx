import React from 'react';
import { withRouter } from 'react-router-dom';
import { useHistory } from "react-router-dom";
import { Redirect } from "react-router-dom";
import './login.css'
import authenticate from '../authenticate/authenticate';


class Login extends React.Component{
    constructor(probs) {
        super(probs);
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
        // let history = useHistory();
      
        event.preventDefault();
      
        if(this.validate()){
          
            console.log(this.state);
            let input = {};
            input["password"] = "";
            input["email"] = "";
            this.setState({input:input});
            if(authenticate.checkPwd(this.state.input['email'],this.state.input['password'])){
              localStorage.setItem('userInfo', this.state.input['email']);
            }else{
              alert('login fail check password or email');
            }
            
          
        }
      }

      handleChangePage = () =>
      {
        window.location.href='/Login';
        window.location.reload(false);
        alert("You can now choose the booking list to book");
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
      
          this.setState({
            errors: errors
          });
      
          return isValid;
      }

  render() {
    return (

        <div>
        <h2>Login User</h2>
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
        <label htmlFor="email">Password</label>
        <input
          name="password"
          type="password"
          id ="password"
          placeholder="Enter your password"
          value={this.state.input.password}
          onChange={this.handleChange}
        />
        <div className="text-danger">{this.state.errors.password}</div>
        <button className= "login_but" type="submit" onClick= {this.handleChangePage}>Login</button>
        <button className="login_but" type="button" onClick= {event =>  window.location.href='/login'} >Back </button>
        
      </form>
      </div>
    );
}

}

export default Login


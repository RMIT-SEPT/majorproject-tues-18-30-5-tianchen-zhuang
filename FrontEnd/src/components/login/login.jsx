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
        
      async handleSubmit(event) {
        // let history = useHistory();
      
        event.preventDefault();
      
        if(this.validate()){
          
            console.log(this.state);
            let input = {};
            input["password"] = "";
            input["email"] = "";
            this.setState({input:input});
          //define email and password
            let e = this.state.input['email'];
            let p = this.state.input['password'];
            //get user api
            let info =  authenticate.getApi(this.state.input['email']);
            //set user info set = set, remove = remove , getItem ==get(name)
            sessionStorage.setItem('user', info);
            
            // localStorage.setItem(user, info);
            info.then((response) => {
              sessionStorage.setItem('username', response.data.username);
                    if(p==response.data.password){
                    sessionStorage.setItem('userInfo', this.state.input['email']);
                    this.props.history.push('/bookinglist');
                  } else{
                    alert('false login fail', this.state.input['password']);
                  }
                  
              });
            
          
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
        <button className= "login_but"type="submit">Login</button>
        <button className="login_but" type="button" onClick= {event =>  window.location.href='/login'} >Back </button>
        
      </form>
      </div>
    );
}

}

export default Login


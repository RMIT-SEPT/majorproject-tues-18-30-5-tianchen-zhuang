import axios from 'axios'

class Authenticate{

    checkPwd(username, password){
    //fixed username and password
    if(username == 'sept@sept.com' && password=="password"){
        return "login success";

    }else{
        return "login fail";
    }

    }

}


export default new Authenticate()
import axios from 'axios'

class Authenticate{

    checkPwd(username, password){
    //fixed username and password
    if(username == 'sept@sept.com' && password=="password"){
        return true;
    }else{
        return false;
    }

    }

}


export default new Authenticate()
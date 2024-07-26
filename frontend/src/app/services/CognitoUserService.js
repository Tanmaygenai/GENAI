import axios from 'axios';
import { Component } from 'react';
const cognitoUserURL = process.env.REACT_APP_Get_Cognito_Users_URL;
const deleteCognitoUserURL = process.env.REACT_APP_Delete_Cognito_User_URL;
const userSignupURL = process.env.REACT_APP_Cognito_User_Signup_URL;

class CognitoUserService extends Component {
  getCognitoUsers(headers) {
    return axios.get(cognitoUserURL, {
      headers: headers
    })
  }

  deleteCognitoUser(data, headers) {
    return axios.post(deleteCognitoUserURL,data,{
      headers: headers
    })
  }

  cognitoUserSignup(data, headers) {
    return axios.post(userSignupURL,data,{
      headers: headers
    })
  }
}
export default new CognitoUserService()
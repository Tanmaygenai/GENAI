import axios from 'axios';
import { Component } from 'react';
const loginURL = process.env.REACT_APP_Login_URL;
const createUserURL = process.env.REACT_APP_CreateUser_URL;
const getUserURL = process.env.REACT_APP_GetUser_URL;
const updateUserURL = process.env.REACT_APP_UpdateUser_URL;
const deleteUserURl = process.env.REACT_APP_DeleteUser_URL;
const loginUserURL = process.env.REACT_APP_Login_User_URL
const getUserLoginURL = process.env.REACT_APP_GetLogin_User_URL
class LoginService extends Component {

  verifyCredentials(data, headers) {
    return axios.post(loginURL, data, {
      headers: headers
    })
  }

  createUser(data, headers) {
    return axios.post(createUserURL, data, {
      headers: headers
    })
  }

  getUser(data, headers) {
    return axios.post(getUserURL, data, {
      headers: headers
    })
  }
  deleteUser(data, headers) {
    return axios.post(deleteUserURl, data, {
      headers: headers
    })
  }
  updateUser(data, headers) {
    return axios.post(updateUserURL, data, {
      headers: headers
    })
  }
  loginUserInsert(data, headers) {
    return axios.post(loginUserURL, data, {
      headers: headers
    })
  }
  getLoginUser(headers) {
    return axios.get(getUserLoginURL, {
      headers: headers
    })
  }
}
export default new LoginService()
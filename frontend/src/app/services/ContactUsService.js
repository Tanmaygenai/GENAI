import axios from 'axios';
import { Component } from 'react';
const contactUsServiceURL = process.env.REACT_APP_ContactUs_URL;
class ContactUsService extends Component {

  contactUs(data, headers) {
    return axios.post(contactUsServiceURL, data, {
      headers: headers
    });
  }
}
export default new ContactUsService()
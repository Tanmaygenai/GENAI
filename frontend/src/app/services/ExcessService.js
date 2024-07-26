import axios from 'axios';
import { Component } from 'react';
const excessDataURL = process.env.REACT_APP_Excess_URL;
class ExcessService extends Component {

  sendExcessData(responseJson, headers) {
    return axios.post(excessDataURL, responseJson, {
      headers: headers
    });
  }
}
export default new ExcessService()
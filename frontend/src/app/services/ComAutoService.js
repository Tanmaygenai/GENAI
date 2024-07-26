import axios from 'axios';
import { Component } from 'react';
const comAutoSubmitURL = process.env.REACT_APP_ComAutoSubmission_URL;
class ComAutoService extends Component {

  sendComAutoData(responseJson, headers) {
    return axios.post(comAutoSubmitURL, responseJson, {
      headers: headers
    });
  }
}
export default new ComAutoService()
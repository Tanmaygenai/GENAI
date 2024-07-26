import axios from 'axios';
import { Component } from 'react';
const comAutoDataURL = process.env.REACT_APP_ComAutoQuickIndication_URL;
const comAutoDataEndorseURL = process.env.REACT_APP_POST_Endorsements_URL;
class ComAutoIndicationService extends Component {

  getPremiumDetails(data, headers) {
    return axios.post(comAutoDataURL, data, {
      headers: headers
    });
  }
  getPremiumDetailsEndorse(policyNumber, data, headers) {
    return axios.post(comAutoDataEndorseURL+policyNumber, data, {
      headers: headers
    });
  }
}
export default new ComAutoIndicationService()
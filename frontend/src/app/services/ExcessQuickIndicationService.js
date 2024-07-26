import axios from 'axios';
import { Component } from 'react';
const excessQuickIndicationDataURL = process.env.REACT_APP_ExcessQuickIndication_URL;
class ExcessQuickIndicationService extends Component {

  getPremiumDetails(data, headers) {
    return axios.post(excessQuickIndicationDataURL, data, {
      headers: headers
    });
  }
}
export default new ExcessQuickIndicationService()
import axios from 'axios';
import { Component } from 'react';
const comPropDataURL = process.env.REACT_APP_ComPropQuickIndication_URL;
class ComPropertyIndicationService extends Component {

  getPremiumDetails(data, headers) {
    return axios.post(comPropDataURL, data, {
      headers: headers
    });
  }
}
export default new ComPropertyIndicationService()
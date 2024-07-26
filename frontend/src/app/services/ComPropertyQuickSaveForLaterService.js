import axios from 'axios';
import { Component } from 'react';
const comPropDataURL = process.env.REACT_APP_ComPropQuickIndication_SaveForLater_URL;
class ComPropertyQuickSaveForLaterService extends Component {

  saveDetails(data, headers) {
    return axios.post(comPropDataURL, data, {
      headers: headers
    });
  }
}
export default new ComPropertyQuickSaveForLaterService()
import axios from 'axios';
import { Component } from 'react';
const vinDetailsURL = process.env.REACT_APP_VIN_DETAILS_URL;
class VINService extends Component {
       fetchVINDetails(vin, headers) {
              return axios.post(vinDetailsURL, vin, {
                     headers: headers
              });
       }
}
export default new VINService()
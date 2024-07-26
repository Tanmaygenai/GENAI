import { Component } from 'react';

import axios from 'axios';
const ISO__URL = process.env.REACT_APP_ISO_URL;
class ISOService extends Component {

    getFireLineDetails(data, headers) {
       
  
        return axios.post(ISO__URL, data, {
            headers: headers
        });
    }
}
export default new ISOService()
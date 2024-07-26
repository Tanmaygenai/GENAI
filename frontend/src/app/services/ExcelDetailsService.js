import { Component } from 'react';
import axios from 'axios';
const downloadDriverDetailsUrl = process.env.REACT_APP_Download_Driver_Details_URL;

class ExcelDetailsService extends Component {

  downloadDocument(headers,trackPath) {
    return axios.get(downloadDriverDetailsUrl ,{
      headers: headers, responseType: 'blob',
      params: {trackPath:trackPath}
    });
  }
}
export default new ExcelDetailsService()
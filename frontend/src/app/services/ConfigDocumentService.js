import { Component } from 'react';
import axios from 'axios';
const getConfigUrl = process.env.REACT_APP_GET_ConfigDocument_URL;
const downloadConfigDocUrl = process.env.REACT_APP_Download_ConfigDocument_URL;
const postConfigUrl = process.env.REACT_APP_POST_ConfigDocument_URL
class ConfigDocumentService extends Component {
  uploadDocument(data, headers) {
    return axios.post(postConfigUrl, data, {
      headers: headers
    });
  }
  getDocument(headers) {
    return axios.get(getConfigUrl, {
      headers: headers
    });
  }
  downloadDocument(headers) {
    return axios.get(downloadConfigDocUrl, {
      headers: headers, responseType: 'blob'
    });
  }
}
export default new ConfigDocumentService()
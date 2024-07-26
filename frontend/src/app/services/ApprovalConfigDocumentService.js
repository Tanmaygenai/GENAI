import { Component } from 'react';
import axios from 'axios';
const getApprovalConfigDocUrl = process.env.REACT_APP_GET_ApprovalConfigDocument_URL;
const downloadApprovalConfigDocUrl = process.env.REACT_APP_Download_ApprovalConfigDocument_URL;
const postApprovalConfigDocUrl = process.env.REACT_APP_POST_ApprovalConfigDocument_URL;
class ApprovalConfigDocumentService extends Component {
  uploadDocument(data, headers) {
    return axios.post(postApprovalConfigDocUrl, data, {
      headers: headers
    });
  }
  getDocument(headers) {
    return axios.get(getApprovalConfigDocUrl, {
      headers: headers
    });
  }
  downloadDocument(headers) {
    return axios.get(downloadApprovalConfigDocUrl, {
      headers: headers, responseType: 'blob'
    });
  }
}
export default new ApprovalConfigDocumentService()
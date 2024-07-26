import axios from 'axios';
import { Component } from 'react';
const policyDBSearchURL = process.env.REACT_APP_PolicyDBSearch_URL;
const policyDataDownloadSearch = process.env.REACT_APP_dataDownloadSearch_URL
const cabinateFileDownloadURL = process.env.REACT_APP_Policy_FileDownload_URL;
const createPolicyURL = process.env.REACT_APP_Policy_Create_URL;
class PolicyService extends Component {
  getPolicyFromDB(quoteId, headers) {
    return axios.post(policyDBSearchURL, quoteId, {
      headers: headers
    });
  }
  getPolicyFileDownload(id, headers) {
    return axios.get(cabinateFileDownloadURL + id, {
      headers: headers, responseType: 'blob'
    });
  }
  createPolicy(data, headers) {
    return axios.post(createPolicyURL, data, {
      headers: headers
    });
  }
}

export default new PolicyService()
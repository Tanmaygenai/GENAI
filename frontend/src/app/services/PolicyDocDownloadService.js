
import axios from 'axios';
import { Component } from 'react';
const policySearchURL= process.env.REACT_APP_PolicyDocDownload_URL;

class PolicyDocDownloadService extends Component {

       getPolicyDocById(systemId,headers){
            return axios.get(policySearchURL+systemId,{
               headers: headers
             });
       }

       }
       export default new PolicyDocDownloadService()
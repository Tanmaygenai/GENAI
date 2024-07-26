import axios from 'axios';
import { Component } from 'react';
const cabinetURL= process.env.REACT_APP_CabinetDownload_URL;

class CabinetService extends Component {

       getCabinet(policyId){
        return axios.get(cabinetURL);
       }
       }
       export default new CabinetService()
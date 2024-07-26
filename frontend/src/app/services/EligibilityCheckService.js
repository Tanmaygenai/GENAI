import axios from 'axios';
import { Component } from 'react';
const eligibilityCheckURL=  process.env.REACT_APP_EligibilityCheck_URL;
class EligibilityCheckService extends Component {
   
       checkEligibility(data){
      //  alert("eligibilityCheckURL"+eligibilityCheckURL);
     return axios.post(eligibilityCheckURL,data);
       }
       }
       export default new EligibilityCheckService()
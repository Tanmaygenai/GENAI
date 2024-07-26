import axios from 'axios';
import { Component } from 'react';
const claimDBSearchURL= process.env.REACT_APP_ClaimDBSearch_URL;
class ClaimService extends Component {
       getClaimsFromDB(policyId,headers){
        return axios.get(claimDBSearchURL+policyId ,{
              headers: headers
            } );
       }

       }
export default new ClaimService()
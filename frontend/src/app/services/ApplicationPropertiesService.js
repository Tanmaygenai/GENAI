import { Component } from 'react';
import axios from 'axios';
const Application_Properties_URL=  process.env.REACT_APP_applicationProperties_URL;
class ApplicationPropertiesService extends Component {
   
  updateCarrierMode(data,headers){
     return axios.post(Application_Properties_URL,data,{
        headers: headers
      });
    }
    }
    export default new ApplicationPropertiesService()
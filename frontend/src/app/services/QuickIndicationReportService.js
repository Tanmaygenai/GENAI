import axios from 'axios';
import { Component } from 'react';
const quickIndicationReportURL=  process.env.REACT_APP_QuickIndicationReport_URL;

class QuickIndicationReportService extends Component {

    getQuickIndicationDetails(headers,startDate,endDate,productType,roleType){
       return axios.get(quickIndicationReportURL,{
         headers: headers,
         params: {startDate: startDate, endDate: endDate, productType: productType, roleType: roleType}

       } )
    }
 }
 export default new QuickIndicationReportService()
import axios from 'axios';
import { Component } from 'react';
const quoteSubmissionPremiumReportURL=process.env.REACT_APP_QuoteSubmissionPremiumReport_URL;

class QuoteSubmissionPremiumReportService extends Component {

    getQuoteSubmissionPremiumDetails(headers,startDate,endDate,productType,roleType){
       return axios.get(quoteSubmissionPremiumReportURL,{
         headers: headers,
         params: {startDate: startDate, endDate: endDate, productType: productType,roleType: roleType}

       } )
    }
 }
 export default new QuoteSubmissionPremiumReportService()
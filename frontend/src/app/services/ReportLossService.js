import axios from 'axios';
import { Component } from 'react';
const reportLossServiceURL = process.env.REACT_APP_ReportLoss_URL;
const reportLossNoticeURL = process.env.REACT_APP_LossReports_URL;
class ReportLossService extends Component {

  reportLoss(data, headers) {
    //  alert("in reportloss class");
    return axios.post(reportLossServiceURL, data, {
      headers: headers
    });
  }

  lossNoticeReports(headers,startDate,endDate,roleType){
    return axios.get(reportLossNoticeURL, {
      headers: headers,
      params: {startDate: startDate, endDate: endDate, roleType: roleType}
    });
  }
}
export default new ReportLossService()
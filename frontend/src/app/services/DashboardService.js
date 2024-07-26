import axios from 'axios';
import { Component } from 'react';
const dashboardChartURL = process.env.REACT_APP_Dashboard_Chart_URL;
const dashboardURL = process.env.REACT_APP_OPEN_WORK_ITEM_DB_URL;
  const monthlyReportURL =
    process.env.REACT_APP_MONTHLY_SUBMISSION_REPORT_DB_URL;
  const totalPremiumReportURL =
    process.env.REACT_APP_TOTAL_PREMIUM_REPORT_DB_URL;
  const monthlyPremiumChartURL =
    process.env.REACT_APP_MONTHLY_PREMIUM_CHART_URL;
  const lossNoticeCountURL = process.env.REACT_APP_LNCount_URL;
  const quoteReportURL = process.env.REACT_APP_Quote_Report_DB_URL;
class DashboradService extends Component {

    getChartData(data, headers) {
        return axios.post(dashboardChartURL, data, {
            headers: headers,
        });
    }

    getLossNoticeCount(data, headers) {
        return axios.post(lossNoticeCountURL, data, {
            headers: headers,
        });
    }

    getMonthlyReport(headers,roleType) {
        return axios.get(monthlyReportURL,{
            headers: headers,
            params: {roleType: roleType}
        });
    }

    getTotalPremiumReport(headers,roleType) {
        return axios.get(totalPremiumReportURL,{
            headers: headers,
            params: {roleType: roleType}
        });
    }

    getDashboardReport(data,headers) {
        return axios.post(dashboardURL,data,{
            headers: headers,
        });
    }
    getMonthlyPremiumCharReport(headers,roleType) {
        return axios.get(monthlyPremiumChartURL,{
            headers: headers,
            params: {roleType: roleType}
        });
    }
    getQuoteReport(headers){
        return axios.post(quoteReportURL,headers,{
            headers: headers
        });
    }
}
export default new DashboradService()
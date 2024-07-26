import axios from 'axios';
import { Component } from 'react';
const recentViewedDBURL=  process.env.REACT_APP_RECENT_VIEW_DB_URL;

class RecentListService extends Component {

    getRecentListDB(headers){
        return axios.get(recentViewedDBURL,{
          headers: headers
        })
     }
 }
 export default new RecentListService()
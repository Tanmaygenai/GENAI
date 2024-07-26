import React, { Component } from 'react';
import axios from 'axios';
const dataDownloadSearchURL = process.env.REACT_APP_dataDownloadSearch_URL;

class DataDownloadService extends Component {

  dataDownloadSearch(systemId, headers) {
    return axios.get(dataDownloadSearchURL, {
      headers: headers,
      params: { systemId: systemId }
    });


  }

}

export default new DataDownloadService();

import axios from 'axios';
import { Component } from 'react';
const producerInfoURL = process.env.REACT_APP_GetProducerInfo_URL;
const createProducerURL = process.env.REACT_APP_CreateProducer_URL;

class ProducerManagementService extends Component {

    getProducerInfo(data, headers) {
        return axios.get(producerInfoURL, {
            headers: headers,
            params: { producerType: data.autoProducerTypeSelect, producerCode: data.producerCode }
        });
    }
    createProducer(data, headers) {
        return axios.post(createProducerURL, data, {
            headers: headers
        });
    }
}
export default new ProducerManagementService()





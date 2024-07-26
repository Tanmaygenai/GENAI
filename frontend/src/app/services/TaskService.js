import axios from 'axios';
import { Component } from 'react';
const taskDataURL = process.env.REACT_APP_GET_Tasks_URL;

class TaskService extends Component {

    getTaskData(headers) {
        return axios.get(taskDataURL, {
            headers: headers,
        });
    }

}
export default new TaskService()
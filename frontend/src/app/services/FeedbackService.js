import React, { Component } from 'react';
import axios from 'axios';
const submitFeedbackURL=process.env.REACT_APP_AGENT_FEEDBACK_URL;
const getFeedbackURL=process.env.REACT_APP_GET_AGENT_FEEDBACK_URL;
class FeedbackService extends Component {
    agentFeedback(data, headers) {
        return axios.post(submitFeedbackURL, data, {
          headers: headers
        });
      }

      getAgentFeedback( headers) {
        return axios.get(getFeedbackURL, {
          headers: headers
        });
      }
}
export default new FeedbackService ()
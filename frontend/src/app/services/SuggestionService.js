import React, { Component } from 'react';
import axios from 'axios';
const submitSuggestionURL=process.env.REACT_APP_AGENT_SUGGESTION_URL
const getSuggestionUrl= process.env.REACT_APP_GET_AGENT_SUGGESTION_URL
class SuggestionService extends Component {
    agentSuggestion(data, headers) {
        return axios.post(submitSuggestionURL, data, {
          headers: headers
        });
      }
      getAgentSuggestion( headers) {
        return axios.get(getSuggestionUrl, {
          headers: headers
        });
      }
}
export default new SuggestionService ()

package com.exavalu.agentportal.model.GWINPolicyDownload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "questionSourceMDA",
    "questionReply"
})
@Generated("jsonschema2pojo")
public class QuestionReplies {

    @JsonProperty("id")
    private String id;
    @JsonProperty("questionSourceMDA")
    private String questionSourceMDA;
    @JsonProperty("questionReply")
    private List<QuestionReply> questionReply = new ArrayList<QuestionReply>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public QuestionReplies withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("questionSourceMDA")
    public String getQuestionSourceMDA() {
        return questionSourceMDA;
    }

    @JsonProperty("questionSourceMDA")
    public void setQuestionSourceMDA(String questionSourceMDA) {
        this.questionSourceMDA = questionSourceMDA;
    }

    public QuestionReplies withQuestionSourceMDA(String questionSourceMDA) {
        this.questionSourceMDA = questionSourceMDA;
        return this;
    }

    @JsonProperty("questionReply")
    public List<QuestionReply> getQuestionReply() {
        return questionReply;
    }

    @JsonProperty("questionReply")
    public void setQuestionReply(List<QuestionReply> questionReply) {
        this.questionReply = questionReply;
    }

    public QuestionReplies withQuestionReply(List<QuestionReply> questionReply) {
        this.questionReply = questionReply;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public QuestionReplies withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(QuestionReplies.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("questionSourceMDA");
        sb.append('=');
        sb.append(((this.questionSourceMDA == null)?"<null>":this.questionSourceMDA));
        sb.append(',');
        sb.append("questionReply");
        sb.append('=');
        sb.append(((this.questionReply == null)?"<null>":this.questionReply));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.questionSourceMDA == null)? 0 :this.questionSourceMDA.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.questionReply == null)? 0 :this.questionReply.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QuestionReplies) == false) {
            return false;
        }
        QuestionReplies rhs = ((QuestionReplies) other);
        return (((((this.questionSourceMDA == rhs.questionSourceMDA)||((this.questionSourceMDA!= null)&&this.questionSourceMDA.equals(rhs.questionSourceMDA)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.questionReply == rhs.questionReply)||((this.questionReply!= null)&&this.questionReply.equals(rhs.questionReply))));
    }

}

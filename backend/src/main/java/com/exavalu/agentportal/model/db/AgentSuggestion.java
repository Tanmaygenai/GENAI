package com.exavalu.agentportal.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agent_suggestion")
public class AgentSuggestion implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agent_suggestion_id")
	private int id;
	@Column(name="suggestion",columnDefinition="TEXT")
	private String suggestion;
	@Column(name="user_name")
	private String userName;
	@Column(name="suggestion_date")
	private String suggestionDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSuggestionDate() {
		return suggestionDate;
	}
	public void setSuggestionDate(String suggestionDate) {
		this.suggestionDate = suggestionDate;
	}
}

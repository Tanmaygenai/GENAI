package com.exavalu.agentportal.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity(name="TaskEntity")
@Table(name = "task")

@SqlResultSetMapping(name="updateTask", columns = { @ColumnResult(name = "count")})
@NamedNativeQueries({
    @NamedNativeQuery(
        name  = "updateTaskDescription",
        query = "UPDATE task SET quote_number = ?, task_description = ? WHERE id = ?"
        ,resultSetMapping = "updateResult"
    )
})

public class Task {
	
	public Task() {}

	@Id
	private int id;
	@Column(name = "agent_email")
	private String agentEmail;
	@Column(name = "agent_id")
	private String agentId;
	@Column(name = "quote_number")
	private String quoteNumber;
	@Column(name = "creation_date")
	private String creationDate;
	@Column(name = "reminder_date")
	private String reminderDate;
	@Column(name = "task_id")
	private String taskId;
	@Column(name = "task_description")
	private String taskDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(String reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Task(String agentEmail, String agentId, String quoteNumber, String creationDate, String reminderDate,
			String taskId, String taskDescription) {
		super();
		this.agentEmail = agentEmail;
		this.agentId = agentId;
		this.quoteNumber = quoteNumber;
		this.creationDate = creationDate;
		this.reminderDate = reminderDate;
		this.taskId = taskId;
		this.taskDescription = taskDescription;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", agentEmail=" + agentEmail + ", agentId=" + agentId + ", quoteNumber=" + quoteNumber
				+ ", creationDate=" + creationDate + ", reminderDate=" + reminderDate + ", taskId=" + taskId
				+ ", taskDescription=" + taskDescription + "]";
	}

}

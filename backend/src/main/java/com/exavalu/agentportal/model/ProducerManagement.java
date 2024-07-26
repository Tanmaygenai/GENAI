package com.exavalu.agentportal.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producer_info")
public class ProducerManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int producer_info_id;
	@Column(name = "producer_code")
	private String producer_code;
	@Column(name = "agency_code")
	private String agency_code;
	@Column(name = "group_code")
	private String group_code;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;

	public int getProducer_info_id() {
		return producer_info_id;
	}

	public void setProducer_info_id(int producer_info_id) {
		this.producer_info_id = producer_info_id;
	}

	public String getProducer_code() {
		return producer_code;
	}

	public void setProducer_code(String producer_code) {
		this.producer_code = producer_code;
	}

	public String getAgency_code() {
		return agency_code;
	}

	public void setAgency_code(String agency_code) {
		this.agency_code = agency_code;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

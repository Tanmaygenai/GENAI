package com.exavalu.agentportal.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "contentmanagement")
public class ContentManagement {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")

	private String id;

	private String name;

	private String type;

	@Lob
	private byte[] filecontent;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	private String classification;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}

	public ContentManagement(String name, String type, byte[] filecontent, String description, String classification) {
		super();
		this.name = name;
		this.type = type;
		this.filecontent = filecontent;
		this.description=description;
		this.classification = classification;
		// TODO Auto-generated constructor stub
	}

	public ContentManagement(String id, String name, String type, byte[] filecontent) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.filecontent = filecontent;

	}

	public ContentManagement() {
		super();
		// TODO Auto-generated constructor stub
	}





}

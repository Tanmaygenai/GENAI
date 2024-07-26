package com.exavalu.agentportal.model;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "documentcontainer")
public class DocumentContainer {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] filecontent;
    private String description;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    private String policyNumber;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
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

    public DocumentContainer(String name, String type, byte[] filecontent, String description, String policyNumber) {
        super();
        this.name = name;
        this.type = type;
        this.filecontent = filecontent;
        this.description=description;
        this.policyNumber=policyNumber;
        // TODO Auto-generated constructor stub
    }

    public DocumentContainer(String id, String name, String type, byte[] filecontent) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.filecontent = filecontent;

    }

    public DocumentContainer() {
        super();
        // TODO Auto-generated constructor stub
    }





}


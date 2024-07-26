package com.exavalu.agentportal.error;

public class PolicyDocumentNotFoundException extends RuntimeException{

	 public PolicyDocumentNotFoundException() {
	        super();
	    }
	 public PolicyDocumentNotFoundException(String id ){
	        super("Policy Document Not Found:"+id);
	    }
	}


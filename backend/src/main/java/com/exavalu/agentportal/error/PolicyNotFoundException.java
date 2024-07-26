package com.exavalu.agentportal.error;

public class PolicyNotFoundException extends RuntimeException{

	 public PolicyNotFoundException() {
	        super();
	    }
	 public PolicyNotFoundException(String id ){
	        super("Policy Not Found:"+id);
	    }

	}


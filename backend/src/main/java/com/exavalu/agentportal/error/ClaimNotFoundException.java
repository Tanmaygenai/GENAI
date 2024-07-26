package com.exavalu.agentportal.error;

public class ClaimNotFoundException extends RuntimeException{

    public ClaimNotFoundException() {
        super();
    }
    public ClaimNotFoundException(String id ){
        super("No Claim is associated with the Policy Number:"+id);
    }
}

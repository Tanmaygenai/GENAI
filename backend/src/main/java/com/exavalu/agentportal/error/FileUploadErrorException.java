package com.exavalu.agentportal.error;

public class FileUploadErrorException extends RuntimeException{

    public FileUploadErrorException() {
        super();
    }
    public FileUploadErrorException(String msg){
        super(msg);
    }

}



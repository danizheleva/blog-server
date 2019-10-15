package com.danielazheleva.blog.models.responce;

public enum ErrorMessages {

    NO_RECORD_FOUND("Record with provided ID is not found");

    private String errorMsg;

    ErrorMessages(String errorMsg){
        this.errorMsg = errorMsg;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

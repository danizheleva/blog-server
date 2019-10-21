package com.danielazheleva.blog.models.responce;

public enum ErrorMessages {

    NO_RECORD_FOUND("Record with provided ID is not found"),
    DAY_OUT_OF_BOUND("New day number exceeds the length of the trip");

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

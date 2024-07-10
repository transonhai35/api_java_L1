package com.globits.da.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseDto {

    private Object data;
    private String errorCode;
    private List<String[]> errorMessage;

    public ResponseDto(Object data, String errorCode, List<String[]> errorMsg) {
        this.data = data;
        this.errorCode = errorCode;

        // Convert the raw values to ErrorDetail objects
        if (errorMsg != null) {
            this.errorMessage = errorMsg;
        }

    }

    //getter and setter

    public Object getData() {
        return data;
    }

    void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<String[]> getErrorMessage() {
        return errorMessage;
    }

    void setErrorMessage(List<String[]> errorMessage) {
        this.errorMessage = errorMessage;
    }

}

package com.globits.da.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ResponseDto {

    private List<ErrorDetailDto> errors;

    public ResponseDto(List<ErrorDetailDto> errors) {
        this.errors = errors;
    }

    public List<ErrorDetailDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetailDto> errors) {
        this.errors = errors;
    }


}

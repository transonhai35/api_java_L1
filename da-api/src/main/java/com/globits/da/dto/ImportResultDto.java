package com.globits.da.dto;

import com.globits.da.domain.Employee;

import java.util.List;

public class ImportResultDto {

    private List<Employee> validEmployees;
    private List<String> errorMessages;

    // Constructors, getters, setters
    public ImportResultDto() {
    }

    public ImportResultDto(List<Employee> validEmployees, List<String> errorMessages) {
        this.validEmployees = validEmployees;
        this.errorMessages = errorMessages;
    }

    public List<Employee> getValidEmployees() {
        return validEmployees;
    }

    public void setValidEmployees(List<Employee> validEmployees) {
        this.validEmployees = validEmployees;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}

package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.domain.Employee;
import com.globits.da.dto.ImportResultDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import org.springframework.stereotype.Service;

import com.globits.da.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;

@Service
public interface EmployeeService extends GenericService<Employee, Long> {

    public  List<EmployeeDto> getAllEmployee();
    public  List<EmployeeDto> searchEmployee(EmployeeSearchDto dto);
    public Boolean destroyEmployee(Long id);
    public EmployeeDto saveEmployee(EmployeeDto employeeDto, Long id);
    public ImportResultDto saveEmployeesFromExcel(List<EmployeeDto> dto);

}

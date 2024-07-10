package com.globits.da.rest;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ImportResultDto;
import com.globits.da.dto.ResponseDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.ExcelHelper;
import com.globits.da.utils.validation.ExcelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee")
public class RestEmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> result = employeeService.getAllEmployee();
        return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchEmployee", method = RequestMethod.POST)
    public ResponseEntity<List<EmployeeDto>> searchEmployee(@RequestBody EmployeeSearchDto dto) {
        List<EmployeeDto> result = employeeService.searchEmployee(dto);
        return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyEmployee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyEmployee(@PathVariable Long id) {
        Boolean result = employeeService.destroyEmployee(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveEmloyee", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        EmployeeDto result = employeeService.saveEmployee(employeeDto,null);
        return new ResponseEntity<EmployeeDto>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/saveEmloyee/{id}", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> saveEmployeeById(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable Long id) {
        EmployeeDto result = employeeService.saveEmployee(employeeDto,id);
        return new ResponseEntity<EmployeeDto>(result, HttpStatus.CREATED);
    }

    @ExceptionHandler(BindException.class)
    public  ResponseEntity<ResponseDto> handleBindException(BindException err) {
        List<String[]> listErrorDetails = new ArrayList<>();
        List<String[]> listTypeErrorDetails = new ArrayList<>();
        List<String[]> listTotalErrorDetails = new ArrayList<>();

        if (err.hasErrors()) {

            // Collect validation errors and convert them to raw value pairs
//            listErrorDetails = err.getFieldErrors().stream()
//                    .map(error -> new String[]{error.getField(), error.getDefaultMessage()})
//                    .collect(Collectors.toList());

            listTypeErrorDetails = err.getBindingResult().getAllErrors().stream()
                    .map(error -> new String[]{error.getDefaultMessage()})
                    .collect(Collectors.toList());



            ResponseDto responseDto = new ResponseDto(null, "VALIDATION_ERROR", listTypeErrorDetails);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        ResponseDto responseDto = new ResponseDto(null, "THERE IS NO VALIDATION_ERROR", null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/importEmployeeExcel", method = RequestMethod.POST)
    public ResponseEntity<?> importEmployeesFromExcel(@RequestParam("file") MultipartFile file) {

        List<EmployeeDto> dto = ExcelHelper.excelToEmployees(file);

        ImportResultDto result = employeeService.saveEmployeesFromExcel(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

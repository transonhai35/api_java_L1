package com.globits.da.rest;

import com.globits.da.domain.Employee;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<EmployeeDto>> searchEmployee(EmployeeSearchDto dto) {
        List<EmployeeDto> result = employeeService.searchEmployee(dto);
        return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyEmployee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyEmployee(@PathVariable Long id) {
        Boolean result = employeeService.destroyEmployee(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

}

package com.globits.da.rest;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ErrorDetailDto;
import com.globits.da.dto.ImportResultDto;
import com.globits.da.dto.ResponseDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.ExcelHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception ex) {
        List<ErrorDetailDto> errors = new ArrayList<>();
        errors.add(new ErrorDetailDto("general", ex.getMessage()));
        ResponseDto responseDto = new ResponseDto(errors);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorDetailDto> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String field = error instanceof FieldError ? ((FieldError) error).getField() : "general";
                    String message = error.getDefaultMessage();
                    return new ErrorDetailDto(field, message);
                })
                .collect(Collectors.toList());

        ResponseDto responseDto = new ResponseDto(errors);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/importEmployeeExcel", method = RequestMethod.POST)
    public ResponseEntity<?> importEmployeesFromExcel(@RequestParam("file") MultipartFile file) {

        List<EmployeeDto> dto = ExcelHelper.excelToEmployees(file);

        ImportResultDto result = employeeService.saveEmployeesFromExcel(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/exportEmployeeExcel", method = RequestMethod.GET)
    public ResponseEntity<?> exportEmployees(HttpServletResponse response) throws IOException {
//        ByteArrayInputStream in = employeeService.export();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

        return ResponseEntity.ok(employeeService.export(response));

    }

}

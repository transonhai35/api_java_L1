package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.domain.Employee;
import com.globits.da.dto.search.EmployeeSearchDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeService extends GenericService<Employee, Long> {

    public  List<EmployeeDto> getAllEmployee();
    public  List<EmployeeDto> searchEmployee(EmployeeSearchDto dto);
    public Boolean destroyEmployee(Long id);
}

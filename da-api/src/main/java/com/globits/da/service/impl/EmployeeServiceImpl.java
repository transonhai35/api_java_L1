package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeDto> listEmployee = employeeRepo.getAllEmployee();
        return listEmployee;
    }

    @Override
    public List<EmployeeDto> searchEmployee(EmployeeSearchDto searchDto){
        List<EmployeeDto> listEmployee = employeeRepo.getAllEmployee();
        List<EmployeeDto> resultEmployee = new ArrayList<EmployeeDto>();

        if(searchDto != null) {
            for(EmployeeDto employeeDto : listEmployee){
                if (searchDto.getId() != null) {
                    if(employeeDto.getId().equals(searchDto.getId())){
                        resultEmployee.add(employeeDto);
                    }
                }
                if (searchDto.getCode() != null) {
                    if(employeeDto.getCode().equals(searchDto.getCode())){
                        resultEmployee.add(employeeDto);
                    }
                }
                if (searchDto.getName() != null) {
                    if(employeeDto.getName().equals(searchDto.getName())){
                        resultEmployee.add(employeeDto);
                    }
                }
                if (searchDto.getEmail() != null) {
                    if(employeeDto.getEmail().equals(searchDto.getEmail())){
                        resultEmployee.add(employeeDto);
                    }
                }
                if (searchDto.getPhone() != null) {
                    if(employeeDto.getPhone().equals(searchDto.getPhone())){
                        resultEmployee.add(employeeDto);
                    }
                }
                if (searchDto.getAge() != null) {
                    if(employeeDto.getAge() == searchDto.getAge()){
                        resultEmployee.add(employeeDto);
                    }
                }
            }
            return resultEmployee;
        }

        return null;
    }

    @Override
    public Boolean destroyEmployee(Long id){
        if(id != null ) {
           employeeRepo.deleteById(id);
           return true;
        }
        return false;
    }

}

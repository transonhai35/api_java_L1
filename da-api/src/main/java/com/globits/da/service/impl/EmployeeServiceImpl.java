package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ImportResultDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.EmployeeService;
import com.globits.da.utils.validation.excel.ExcelValidator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
@Transactional
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService {


    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    ProvinceRepository provinceRepo;

    @Autowired
    DistrictRepository districtRepo;

    @Autowired
    CommuneRepository communeRepo;



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

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto, Long id) {

        if(dto == null) {
            return null;
        }

        Employee entity = null;

        if (dto.getId() != null && !dto.getId().equals(id)) {
            return null;
        }

        if (dto.getId() != null) {
            entity = employeeRepo.findById(dto.getId()).orElse(null);
        }

        if (entity == null) {
            entity = new Employee();
        }

        // Save or update Employee
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAge(dto.getAge());
        entity.setProvinceId(dto.getProvinceId());
        entity.setDistrictId(dto.getDistrictId());
        entity.setCommuneId(dto.getCommuneId());

        entity = employeeRepo.save(entity);


        return new EmployeeDto(entity);
    }

        @Override
        public ImportResultDto saveEmployeesFromExcel(List<EmployeeDto> dtos) {
        List<Employee> employees = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();


        if(dtos == null){
            return null;
        }

        int index = 0;
        for (EmployeeDto dto : dtos) {
            if (dto != null) {

                EmployeeDto employeeDto = new EmployeeDto();
                // Set code
                if(employeeRepo.existsByCode(dto.getCode())){
                    errorMessages.add("Code must be unique at element" + index );
                    continue;
                }
                employeeDto.setCode(dto.getCode());

                // Set name
                employeeDto.setName(dto.getName());

                // Set email
                if(!ExcelValidator.validateEmail(dto.getEmail(), errorMessages, index)){
                    continue;
                }
                employeeDto.setEmail(dto.getEmail());

                // Set phone
                if (!ExcelValidator.validatePhone(dto.getPhone(), errorMessages, index)) {
                    continue;
                }
                employeeDto.setPhone(dto.getPhone());

                // Set age
                if (!ExcelValidator.validateAge(dto.getAge(), errorMessages, index)) {
                    continue;
                }
                employeeDto.setAge(dto.getAge());

                // Set provinceId
                if (!provinceRepo.existsById(dto.getProvinceId())) {
                    errorMessages.add("Province not found at row " + index);
                    continue;
                }
                employeeDto.setProvinceId(dto.getProvinceId());

                // Set district and districtId
                if (!districtRepo.existsById(dto.getDistrictId())) {
                    errorMessages.add("District not found at row " + index);
                    continue;
                }


                if (!districtRepo.existsByIdAndProvinceId(dto.getDistrictId(), dto.getProvinceId())) {
                    errorMessages.add("District does not belong to the specified Province at row " + index);
                    continue;
                }

                employeeDto.setDistrictId(dto.getDistrictId());


                // Set commune and communeId
                if (!communeRepo.existsById(dto.getCommuneId())) {
                    errorMessages.add("Commune not found at row " + index);
                    continue;
                }

                if (!communeRepo.existsByIdAndDistrictId(dto.getCommuneId(), dto.getDistrictId())) {
                    errorMessages.add("Commune does not belong to the specified District at row " + index);
                    continue;
                }

                employeeDto.setCommuneId(dto.getCommuneId());


                Employee entity = new Employee();

                // Save or update Employee
                entity.setCode(dto.getCode());
                entity.setName(dto.getName());
                entity.setEmail(dto.getEmail());
                entity.setPhone(dto.getPhone());
                entity.setAge(dto.getAge());
                entity.setProvinceId(dto.getProvinceId());
                entity.setDistrictId(dto.getDistrictId());
                entity.setCommuneId(dto.getCommuneId());

                entity = employeeRepo.save(entity);

                employees.add(entity);
                index++;

            }
        }



        return new ImportResultDto(employees, errorMessages);

    }


}


 
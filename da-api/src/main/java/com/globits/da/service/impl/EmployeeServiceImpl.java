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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;




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
        if(searchDto != null) {
            return employeeRepo.findAllByCriteria(searchDto);
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


    @Override
    public Object export(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeRepo.findAll();

           try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
               Sheet sheet = workbook.createSheet("Employees");

               Row headerRow = sheet.createRow(0);
               String[] headers = {"STT", "Tên", "Mã", "Email", "Phone", "Tuổi"};
               for (int i = 0; i < headers.length; i++) {
                   headerRow.createCell(i).setCellValue(headers[i]);
               }

               int rowIdx = 1;
               int stt = 1;
               for (Employee employee : employees) {
                   Row row = sheet.createRow(rowIdx++);

                   row.createCell(0).setCellValue(stt++);
                   row.createCell(1).setCellValue(employee.getName());
                   row.createCell(2).setCellValue(employee.getCode());
                   row.createCell(3).setCellValue(employee.getEmail());
                   row.createCell(4).setCellValue(employee.getPhone());
                   row.createCell(5).setCellValue(employee.getAge());
               }

               response.setContentType("application/octet-stream");
               String key = "Content-Disposition";
               String value = "attachment;filename=employees.xlsx";
               response.setHeader(key, value);

               ServletOutputStream result = response.getOutputStream();

               workbook.write(result);
               workbook.close();
               result.close();

               return HttpStatus.OK;
           }

    }



}



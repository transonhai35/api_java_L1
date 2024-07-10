package com.globits.da.utils;

import com.globits.da.dto.EmployeeDto;
import com.sun.istack.NotNull;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

            public static List<EmployeeDto> excelToEmployees(MultipartFile file){
                List<EmployeeDto> employees = new ArrayList<>();
                try {
                    Workbook workbook = new XSSFWorkbook(file.getInputStream());
                    Sheet sheet = workbook.getSheetAt(0);

                    for (Row row : sheet) {
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        //Set field already have in Excel file
                        EmployeeDto employeeDto = new EmployeeDto();
                        //set code
                        Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setCode(cell.getStringCellValue());

                        //set name
                        cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setName(cell.getStringCellValue());

                        //set email
                        cell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setEmail(cell.getStringCellValue());

                        //set phone
                        cell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setPhone(cell.getStringCellValue());

                        //set age
                        cell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setAge((int) cell.getNumericCellValue());

                        //set province and provinceId
                        cell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setProvinceId((long) cell.getNumericCellValue() );

                        //set district and districtId
                        cell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setDistrictId((long) cell.getNumericCellValue());


                        //set commune and communeId
                        cell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        employeeDto.setCommuneId((long) cell.getNumericCellValue());


                           employees.add(employeeDto);

                        }


                    return employees;
                } catch (IOException e) {
                    return null;
                }
            }


}




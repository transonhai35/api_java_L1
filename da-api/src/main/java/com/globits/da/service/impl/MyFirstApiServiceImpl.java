package com.globits.da.service.impl;

import com.globits.da.dto.ExportExcelDto;
import com.globits.da.service.MyFirstApiSercive;
import org.springframework.stereotype.Service;
import com.globits.da.dto.MyFirstApiDto;

import com.globits.da.dto.ExportExcelDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;



@Service
public class MyFirstApiServiceImpl implements MyFirstApiSercive {

//    public MyFirstApiDto handlePostRequest(MyFirstApiDto myFirstApiDto) {
//        if(myFirstApiDto != null) {
//            return new MyFirstApiDto(myFirstApiDto) ;
//        }
//        return null;
//    }

    @Override
    public MyFirstApiDto processMyFirstApi(MyFirstApiDto myFirstApiDto) {
        return myFirstApiDto;
    }

    public byte[] exportToExcel(List<ExportExcelDto> dtos) throws IOException {
        String[] columns = {"STT", "Code", "Name", "ID"};

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(createHeaderStyle(workbook));
        }

        int rowNum = 1;
        for (int i = 0; i < dtos.size(); i++) {
            ExportExcelDto dto = dtos.get(i);
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(dto.getCode());
            row.createCell(2).setCellValue(dto.getName());
            row.createCell(3).setCellValue(dto.getId());
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}

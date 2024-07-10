package com.globits.da.rest;

import com.globits.da.dto.ExportExcelDto;
import org.springframework.http.HttpHeaders;
import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.impl.MyFirstApiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/myFirstApi")
public class RestMyFirstApiController {

    @Autowired
    private MyFirstApiServiceImpl myFirstApiSvc;

    @RequestMapping(value = "/postMyFirstApi", method = RequestMethod.POST)
    public ResponseEntity<MyFirstApiDto> save(@RequestBody MyFirstApiDto dto) {
        MyFirstApiDto result = myFirstApiSvc.processMyFirstApi(dto);
        return new ResponseEntity<MyFirstApiDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
        public String uploadFile(@RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return "File is empty";
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload failed: " + e.getMessage();
            }

            return "File uploaded successfully";
        }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        List<ExportExcelDto> result = Arrays.asList(
                new ExportExcelDto("Code001", "Name001", 1),
                new ExportExcelDto("Code002", "Name002", 2),
                new ExportExcelDto("Code003", "Name003", 3)
        );;

        byte[] excelBytes =  myFirstApiSvc.exportToExcel(result);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=employees.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelBytes);
    }



}






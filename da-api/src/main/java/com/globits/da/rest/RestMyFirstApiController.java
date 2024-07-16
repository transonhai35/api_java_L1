package com.globits.da.rest;

import com.globits.da.dto.ExportExcelDto;
import com.globits.da.dto.MyFirstApiDto;
import com.globits.da.service.impl.MyFirstApiServiceImpl;
import com.globits.da.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.io.BufferedReader;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;


@RestController
@RequestMapping("/api/myFirstApi")
public class RestMyFirstApiController {

    @Autowired
    private MyFirstApiServiceImpl myFirstApiSvc;

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public ResponseEntity<String> string() {
        return new ResponseEntity<>("MyFirstApi", HttpStatus.OK);
    }

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

}






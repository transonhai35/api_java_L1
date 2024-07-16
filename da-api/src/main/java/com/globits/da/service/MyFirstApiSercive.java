package com.globits.da.service;

import com.globits.da.dto.ExportExcelDto;
import com.globits.da.dto.MyFirstApiDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface MyFirstApiSercive {
    public MyFirstApiDto processMyFirstApi(MyFirstApiDto myFirstApiDto);
}

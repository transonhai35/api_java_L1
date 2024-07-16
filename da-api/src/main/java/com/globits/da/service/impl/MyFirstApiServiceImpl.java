package com.globits.da.service.impl;

import com.globits.da.service.MyFirstApiSercive;
import org.springframework.stereotype.Service;
import com.globits.da.dto.MyFirstApiDto;



@Service
public class MyFirstApiServiceImpl implements MyFirstApiSercive {

    @Override
    public MyFirstApiDto processMyFirstApi(MyFirstApiDto myFirstApiDto) {
        return myFirstApiDto;
    }

}

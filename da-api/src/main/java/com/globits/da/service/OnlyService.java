package com.globits.da.service;


import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public interface OnlyService {

    //province CRUD
    public List<ProvinceDto> getOnlyProvince();
    public  List<ProvinceDto> searchProvince(ProvinceSearchDto dto);
    public  ProvinceDto saveProvince (ProvinceDto dto,Long id);
    public Boolean destroyProvince(Long id);
}

package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProvinceService extends GenericService<Province, Long> {

    //province CRUD
    public List<ProvinceDto> getOnlyProvince();
    public  List<ProvinceDto> searchProvince(ProvinceSearchDto dto);
    public  ProvinceDto saveProvince (ProvinceDto dto,Long id);
    public Boolean destroyProvince(Long id);




}

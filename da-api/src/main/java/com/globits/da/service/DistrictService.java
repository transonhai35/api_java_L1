package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;

import java.util.List;

public interface DistrictService extends GenericService<District, Long> {

    //distirct CRUD
    public List<DistrictDto> getOnlyDistrict();
    public  List<DistrictDto> searchDistrict(DistrictSearchDto dto);
    public  DistrictDto saveDistrict (DistrictDto dto,Long id);
    public Boolean destroyDistrict(Long id);
    public List<DistrictDto> searchDistrictByIdProvince(Long idProvince);
}

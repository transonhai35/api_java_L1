package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;

import java.util.List;

public interface CommuneService extends GenericService<Commune, Long> {

    //commune CRUD
    public List<CommuneDto> getOnlyCommune();
    public  List<CommuneDto> searchCommune(CommuneSearchDto dto);
    public  List<CommuneDto> searchCommuneByIdDistrict(Long id);
    public  CommuneDto saveCommune (CommuneDto dto,Long id);
    public Boolean destroyCommune(Long id);


}

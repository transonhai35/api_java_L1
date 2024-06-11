package com.globits.da.service.impl;

import com.globits.da.domain.Province;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.EmployeeSearchDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.OnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OnlyServiceImpl implements OnlyService {

    @Autowired
    ProvinceRepository provinceRepo;

    //CRUD province
    @Override
    public List<ProvinceDto> getOnlyProvince(){
        List<ProvinceDto> listProvince = provinceRepo.getOnlyProvince();
        return listProvince;
    }

    @Override
    public List<ProvinceDto> searchProvince(ProvinceSearchDto searchDto){
        List<ProvinceDto> listProvince = provinceRepo.getOnlyProvince();
        List<ProvinceDto> resultProvince = new ArrayList<ProvinceDto>();

        if(searchDto != null) {
            for(ProvinceDto provinceDto : listProvince){
                if (    searchDto.getId() != null
                        || searchDto.getCodeProvince() != null
                        || searchDto.getCodeProvince() != null
                ) {
                    if (provinceDto.getId().equals(searchDto.getId())
                            || provinceDto.getName().equals(searchDto.getName())
                    ) {
                        resultProvince.add(provinceDto);
                    }
                }

            }
            return resultProvince;
        }

        return null;
    }

    @Override
    public ProvinceDto saveProvince (ProvinceDto dto,Long id){

        Boolean isValid = true;
        List<ProvinceDto> listProvince = provinceRepo.getOnlyProvince();

        if(dto != null ) {
            Province entity = null;
            if(dto.getId() != null){
                if (dto.getId() != null && !dto.getId().equals(id)) {
                    return null;
                }
                entity.setName(dto.getName());
                entity = provinceRepo.save(entity);

            }
            if(entity == null) {
                entity = new Province();
            }

            if(id == null) {
                return null;
            }


            if(isValid){
                entity.setName(dto.getName());
                entity = provinceRepo.save(entity);
            }

            if(entity != null) {
                return  new ProvinceDto(entity);
            }
        }
        return null;
    }

    @Override
    public Boolean destroyProvince(Long id){
        if(id != null){
                    provinceRepo.deleteById(id);
                    return true;
        }
        return false;
    }
}

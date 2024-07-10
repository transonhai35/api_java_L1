package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, Long> implements DistrictService {

    @Autowired
    DistrictRepository districtRepo;

    @Autowired
    ProvinceRepository provinceRepo;

    @Autowired
    CommuneRepository communeRepo;


    //CRUD district
    @Override
    public List<DistrictDto> getOnlyDistrict() {
        List<DistrictDto> listDistrict = districtRepo.getDistrict();
        return listDistrict;
    }

    @Override
    public List<DistrictDto> searchDistrict(DistrictSearchDto searchDto) {
        List<DistrictDto> listDistrict = districtRepo.getDistrict();
        List<DistrictDto> resultDistrict = new ArrayList<DistrictDto>();

        if (searchDto != null) {
            for (DistrictDto districtDto : listDistrict) {
                if (searchDto.getId() != null) {
                    if (districtDto.getId().equals(searchDto.getId())
                            || districtDto.getName().equals(searchDto.getName())
                    ) {
                        resultDistrict.add(districtDto);
                    }
                }

            }
            return resultDistrict;
        }

        return null;
    }

    @Override
    public List<DistrictDto> searchDistrictByIdProvince(Long idProvince) {
        List<DistrictDto> resultDistrict;
        resultDistrict = districtRepo.findDistrictByProvinceId(idProvince);
        return resultDistrict;
    }



    @Override
    public DistrictDto saveDistrict(DistrictDto dto, Long id) {
        if (dto != null) {
            District entity = null;

            if (dto.getId() != null && !dto.getId().equals(id)) {
                return null;
            }

            if (dto.getId() != null) {
                entity = districtRepo.findById(dto.getId()).orElse(null);
            }

            if (entity == null) {
                entity = new District();
            }
            entity.setName(dto.getName());


            if (dto.getProvinceId() != null) {
                Province province = provinceRepo.findById(dto.getProvinceId())
                        .orElseThrow(() -> new RuntimeException("Province not found"));
                entity.setProvince(province);
            } else {
                entity.setProvince(null);
            }
            entity = districtRepo.save(entity);

            // Save or update Commune
            if (dto.getCommunes() != null) {
                final District finalEntity = entity;
                List<Commune> communes = dto.getCommunes().stream().map(communeDto -> {
                    Commune commune = null;
                    if (communeDto.getId() != null) {
                        commune = communeRepo.findById(communeDto.getId()).orElse(null);
                    }

                    if (commune == null) {
                        commune = new Commune();
                    }

                    commune.setName(communeDto.getName());
                    commune.setDistrict(finalEntity); // Set parent Province
                    return communeRepo.save(commune);
                }).collect(Collectors.toList());

                // Set the updated list of Districts to the Province entity
                entity.setCommunes(communes);
            }

            return new DistrictDto(entity);
        }
        return null;
    }

    @Override
    public Boolean destroyDistrict(Long id) {
        if (id != null) {
            districtRepo.deleteById(id);
            return true;
        }
        return false;
    }
}

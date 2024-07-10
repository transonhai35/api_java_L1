package com.globits.da.service.impl;

import com.globits.core.service.GenericService;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProvinceServiceImpl extends GenericServiceImpl<Province, Long> implements ProvinceService  {

    @Autowired
    ProvinceRepository provinceRepo;

    @Autowired
    DistrictRepository districtRepo;

    @Autowired
    CommuneRepository communeRepo;

    //CRUD province
    @Override
    public List<ProvinceDto> getOnlyProvince() {
        List<ProvinceDto> listProvince = provinceRepo.getProvince();

        return listProvince;
    }

    @Override
    public List<ProvinceDto> searchProvince(ProvinceSearchDto searchDto) {
        List<ProvinceDto> listProvince = provinceRepo.getProvince();
        List<ProvinceDto> resultProvince = new ArrayList<ProvinceDto>();

        if (searchDto != null) {
            for (ProvinceDto provinceDto : listProvince) {
                if (searchDto.getId() != null) {
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
    public ProvinceDto saveProvince(ProvinceDto dto, Long id) {
        if (dto == null) {
            return null;
        }

        Province entity = null;

        if (dto.getId() != null) {
            entity = provinceRepo.findById(dto.getId()).orElse(null);
        }
        if (entity == null) {
            entity = new Province();
        }

        // Save or update Province
        entity.setName(dto.getName());
        entity = provinceRepo.save(entity);

        // Save or update Districts
        if (dto.getDistricts() != null) {
            final Province finalEntity = entity;
            List<District> districts = dto.getDistricts().stream().map(districtDto -> {
                District district = null;

                // Fetch existing district if ID is provided, otherwise create a new one
                if (districtDto.getId() != null) {
                    district = districtRepo.findById(districtDto.getId()).orElse(null);
                }
                if (district == null) {
                    district = new District();
                }
                district.setName(districtDto.getName());
                district.setProvince(finalEntity);

                // Save or update Communes
                if (districtDto.getCommunes() != null) {
                    final District finalDistrict = district;
                    List<Commune> wards = districtDto.getCommunes().stream().map(communeDto -> {
                        Commune commune = null;

                        // Fetch existing communes if ID is provided, otherwise create a new one
                        if (communeDto.getId() != null) {
                            commune = communeRepo.findById(communeDto.getId()).orElse(null);
                        }
                        if (commune == null) {
                            commune = new Commune();
                        }
                        commune.setName(communeDto.getName());
                        commune.setDistrict(finalDistrict);
                        return communeRepo.save(commune);
                    }).collect(Collectors.toList());

                    district.setCommunes(wards);
                }

                return districtRepo.save(district);
            }).collect(Collectors.toList());

            entity.setDistricts(districts);
        }

        return new ProvinceDto(entity);

    }

    @Override
    public Boolean destroyProvince(Long id) {
        if (id != null) {
            provinceRepo.deleteById(id);
            return true;
        }
        return false;
    }

}


package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommuneServiceImpl extends GenericServiceImpl<Commune, Long> implements CommuneService {

    @Autowired
    CommuneRepository communeRepo;

    @Autowired
    DistrictRepository districtRepo;

    //CRUD commune
    @Override
    public List<CommuneDto> getOnlyCommune() {
        List<CommuneDto> listCommune = communeRepo.getCommune();
        return listCommune;
    }

    @Override
    public List<CommuneDto> searchCommune(CommuneSearchDto searchDto) {
        List<CommuneDto> listCommune = communeRepo.getCommune();
        List<CommuneDto> resultCommune = new ArrayList<CommuneDto>();

        if (searchDto != null) {
            for (CommuneDto communeDto : listCommune) {
                if (searchDto.getId() != null) {
                    if (communeDto.getId().equals(searchDto.getId())
                            || communeDto.getName().equals(searchDto.getName())
                    ) {
                        resultCommune.add(communeDto);
                    }
                }

            }
            return resultCommune;
        }

        return null;
    }

    @Override
    public  List<CommuneDto> searchCommuneByIdDistrict(Long idDistrict){
        List<CommuneDto> resultCommune = communeRepo.findCommnuneByDistrict(idDistrict);
        return resultCommune;
    }

    @Override
    public CommuneDto saveCommune(CommuneDto dto, Long id) {
        if (dto != null) {
            Commune entity = null;

            if (dto.getId() != null && !dto.getId().equals(id)) {
                return null; // Trả về null nếu ID trong DTO không khớp với ID được truyền vào
            }

            if (dto.getId() != null) {
                entity = communeRepo.findById(dto.getId()).orElse(null);
            }

            if (entity == null) {
                entity = new Commune();
            }

            entity.setName(dto.getName());

            if (dto.getDistrictId() != null) {
                District district = districtRepo.findById(dto.getDistrictId())
                        .orElseThrow(() -> new RuntimeException("Province not found"));
                entity.setDistrict(district);
            } else {
                entity.setDistrict(null);
            }
            entity = communeRepo.save(entity);

            return new CommuneDto(entity);
        }
        return null;
    }

    @Override
    public Boolean destroyCommune(Long id) {
        if (id != null) {
            communeRepo.deleteById(id);
            return true;
        }
        return false;
    }



}

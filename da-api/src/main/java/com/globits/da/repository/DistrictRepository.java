package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query("select new com.globits.da.dto.DistrictDto(ed) from District ed")
    List<DistrictDto> getDistrict();

    @Query("SELECT new com.globits.da.dto.DistrictDto(ed) FROM District ed WHERE ed.province.id = :provinceId")
    List<DistrictDto> findDistrictByProvinceId(Long provinceId);

//    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM District ed WHERE ed.id = :id AND ed.province.id = :idProvince")
    boolean existsByIdAndProvinceId(Long id, Long provinceId);

}

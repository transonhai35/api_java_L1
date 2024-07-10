package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    @Query("select new com.globits.da.dto.ProvinceDto(ed) from Province ed")
    List<ProvinceDto> getProvince();


}

package com.globits.da.repository;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {

    @Query("select new com.globits.da.dto.CommuneDto(ed) from Commune ed")
    List<CommuneDto> getCommune();

    @Query("SELECT new com.globits.da.dto.CommuneDto(ed) FROM Commune ed WHERE ed.district.id = :districtId")
    List<CommuneDto> findCommnuneByDistrict(Long districtId);

//    @Query("SELECT CASE WHEN COUNT(ed) > 0 THEN true ELSE false END FROM Commune ed WHERE ed.id = :id AND ed.district.id = :idDistrict")
    boolean existsByIdAndDistrictId(Long id, Long districtId);

}

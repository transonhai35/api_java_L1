package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Query("select new com.globits.da.dto.CertificateDto(ed) from Certificate ed")
    List<CertificateDto> getCertificate();

    @Query("SELECT COUNT(c) FROM Certificate c WHERE c.employee.id = :employeeId AND c.name = :name AND c.expired IS TRUE ")
    long countCertificateStillValidated(@Param("employeeId") Long employeeId, @Param("name") String name);

    @Query("SELECT c FROM Certificate c WHERE c.name = :name AND c.province.id = :provinceId AND c.validTo IS NULL AND c.employee.id = :employeeId")
    Certificate checkExpiredCertificateByProvinceId(@Param("name") String name, @Param("provinceId") Long provinceId, @Param("employeeId") Long employeeId);

}

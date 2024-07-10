package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.*;
import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;

import com.globits.da.repository.CertificateRepository;

import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, Long> implements CertificateService {

    @Autowired
    CertificateRepository certificateRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    ProvinceRepository provinceRepo;

    @Autowired
    CertificateRepository employeeCertificateRepo;


    //CRUD certificate
    @Override
    public List<CertificateDto> getOnlyCertificate() {
        List<CertificateDto> listCertificate = certificateRepo.getCertificate();
        return listCertificate;
    }

    @Override
    public List<CertificateDto> searchCertificate(CertificateSearchDto searchDto) {
        List<CertificateDto> listCertificate = certificateRepo.getCertificate();
        List<CertificateDto> resultCertificate = new ArrayList<CertificateDto>();

        if (searchDto != null) {
            for (CertificateDto provinceDto : listCertificate) {
                if (searchDto.getId() != null) {
                    if (provinceDto.getId().equals(searchDto.getId())
                            || provinceDto.getName().equals(searchDto.getName())
                    ) {
                        resultCertificate.add(provinceDto);
                    }
                }

            }
            return resultCertificate;
        }

        return null;
    }

    @Override
    public List<CertificateDto> saveCertificate(List<CertificateDto> dtos, Long id) {
        if (dtos != null) {

            List<CertificateDto> savedCertificates = new ArrayList<>();

            for(CertificateDto dto : dtos){
                if (dto != null) {
                    Certificate entity = null;

                    //Check the number of valid diplomas of the same type
                    long countValidCertificatesOfType = certificateRepo.countCertificateStillValidated(dto.getEmployeeId(), dto.getName());
                    if (countValidCertificatesOfType >= 3) {

                        continue;
                    }

                    // check valid certificate
                    Certificate existingCertificate = certificateRepo.checkExpiredCertificateByProvinceId(dto.getName(), dto.getProvinceId(), dto.getEmployeeId());
                    if (existingCertificate != null) {
                        continue;
                    }

                    if (dto.getId() != null && !dto.getId().equals(id)) {
                        return null;
                    }

                    if (dto.getId() != null) {
                        entity = certificateRepo.findById(dto.getId()).orElse(null);
                    }

                    if (entity == null) {
                        entity = new Certificate();
                    }

                    // save or update certificate

                    entity.setName(dto.getName());
                    entity.setValidFrom(dto.getValidFrom());

                    entity.setValidTo(dto.getValidTo());
                    entity.setExpired(dto.getExpired());
                    entity.setProvince(provinceRepo.findById(dto.getProvinceId()).orElse(null));
                    entity.setEmployee(employeeRepo.findById(dto.getEmployeeId()).orElse(null));
                    entity = certificateRepo.save(entity);

                    //save EmployeeCertificate
                    EmployeeCertificate employeeCertificate = new EmployeeCertificate();
                    employeeCertificate.setEmployee(employeeRepo.findById(dto.getEmployeeId()).orElse(null));
                    employeeCertificate.setCertificate(entity);
                    employeeCertificate.setProvince(provinceRepo.findById(dto.getProvinceId()).orElse(null));
                    employeeCertificateRepo.save(employeeCertificate);

                    savedCertificates.add(new CertificateDto(entity));
                }
            }
            return savedCertificates;
        }
        return null;
    }

    @Override
    public Boolean destroyCertificate(Long id) {
        if (id != null) {
            certificateRepo.deleteById(id);
            return true;
        }
        return false;
    }


}

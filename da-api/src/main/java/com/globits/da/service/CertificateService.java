package com.globits.da.service;


import com.globits.core.service.GenericService;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.*;
import com.globits.da.dto.search.CertificateSearchDto;

import java.util.List;

public interface CertificateService extends GenericService<Certificate, Long> {

    //certificate CRUD
    public List<CertificateDto> getOnlyCertificate();
    public  List<CertificateDto> searchCertificate(CertificateSearchDto dto);
    public  List<CertificateDto> saveCertificate (List<CertificateDto> dtos,Long id);
    public Boolean destroyCertificate(Long id);

}

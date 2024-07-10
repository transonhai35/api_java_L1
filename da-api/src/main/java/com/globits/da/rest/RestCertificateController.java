package com.globits.da.rest;

import com.globits.da.dto.CertificateDto;
import com.globits.da.dto.search.CertificateSearchDto;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/certificate")
public class RestCertificateController {

    @Autowired
    CertificateService certificateSvc;

    //certificate CRUD
    @RequestMapping(value = "/getCertificate" , method = RequestMethod.GET)
    public ResponseEntity<List<CertificateDto>> getCertificate() {
        List<CertificateDto> result = certificateSvc.getOnlyCertificate();
        return new ResponseEntity<List<CertificateDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCertificate", method = RequestMethod.POST   )
    public  ResponseEntity<List<CertificateDto>> saveCertificate(@RequestBody List<CertificateDto> dtos){
        List<CertificateDto> result = certificateSvc.saveCertificate(dtos,null);
        return new ResponseEntity<List<CertificateDto>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/saveCertificate/{id}", method = RequestMethod.POST   )
    public  ResponseEntity<List<CertificateDto>> saveCertificate(@RequestBody List<CertificateDto> dtos, @PathVariable Long id){
        List<CertificateDto> result = certificateSvc.saveCertificate(dtos,id);
        return new ResponseEntity<List<CertificateDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchCertificate", method = RequestMethod.POST)
    public ResponseEntity<List<CertificateDto>> searchCertificate(@RequestBody CertificateSearchDto dto) {
        List<CertificateDto> result = certificateSvc.searchCertificate(dto);
        return new ResponseEntity<List<CertificateDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyCertificate/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyCertificate(@PathVariable Long id) {
        Boolean result = certificateSvc.destroyCertificate(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);

    }

}


package com.globits.da.rest;

import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class RestProvinceController {

    @Autowired
    ProvinceService provinceSvc;

    //province CRUD
    @RequestMapping(value = "/getProvince" , method = RequestMethod.GET)
    public ResponseEntity<List<ProvinceDto>> getProvince() {
        List<ProvinceDto> result = provinceSvc.getOnlyProvince();
        return new ResponseEntity<List<ProvinceDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveProvince", method = RequestMethod.POST   )
    public  ResponseEntity<ProvinceDto> saveProvince(@RequestBody ProvinceDto dto){
        ProvinceDto result = provinceSvc.saveProvince(dto,null);
        return new ResponseEntity<ProvinceDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveProvince/{id}", method = RequestMethod.POST   )
    public  ResponseEntity<ProvinceDto> saveProvince(@RequestBody ProvinceDto dto,@PathVariable Long id){
        ProvinceDto result = provinceSvc.saveProvince(dto,id);
        return new ResponseEntity<ProvinceDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchProvince", method = RequestMethod.POST)
    public ResponseEntity<List<ProvinceDto>> searchProvince(@RequestBody ProvinceSearchDto dto) {
        List<ProvinceDto> result = provinceSvc.searchProvince(dto);
        return new ResponseEntity<List<ProvinceDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyProvince/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyProvince(@PathVariable Long id) {
        Boolean result = provinceSvc.destroyProvince(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);

    }


}

package com.globits.da.rest;

import com.globits.da.dto.ProvinceDto;
import com.globits.da.dto.search.ProvinceSearchDto;
import com.globits.da.service.OnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/only")
public class RestOnlyController {

    @Autowired
    OnlyService onlySvc;


    //province CRUD
    @RequestMapping(value = "/getProvince" , method = RequestMethod.GET)
    public ResponseEntity<List<ProvinceDto>> getProvince() {
        List<ProvinceDto> result = onlySvc.getOnlyProvince();
        return new ResponseEntity<List<ProvinceDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveProvince", method = RequestMethod.POST   )
    public  ResponseEntity<ProvinceDto> saveProvince(ProvinceDto dto){
        ProvinceDto result = onlySvc.saveProvince(dto,null);
        return new ResponseEntity<ProvinceDto>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/saveProvince/{id}", method = RequestMethod.POST   )
    public  ResponseEntity<ProvinceDto> saveProvince(ProvinceDto dto,@PathVariable Long id){
        ProvinceDto result = onlySvc.saveProvince(dto,id);
        return new ResponseEntity<ProvinceDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchProvince", method = RequestMethod.POST)
    public ResponseEntity<List<ProvinceDto>> searchProvince(ProvinceSearchDto dto) {
        List<ProvinceDto> result = onlySvc.searchProvince(dto);
        return new ResponseEntity<List<ProvinceDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyProvince/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyProvince(@PathVariable Long id) {
        Boolean result = onlySvc.destroyProvince(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);

    }

}


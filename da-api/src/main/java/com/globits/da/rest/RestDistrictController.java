package com.globits.da.rest;

import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.search.DistrictSearchDto;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/district")
public class RestDistrictController {

    @Autowired
    DistrictService districtSvc;

    //District CRUD
    @RequestMapping(value = "/getDistrict" , method = RequestMethod.GET)
    public ResponseEntity<List<DistrictDto>> getDistrict() {
        List<DistrictDto> result = districtSvc.getOnlyDistrict();
        return new ResponseEntity<List<DistrictDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveDistrict", method = RequestMethod.POST   )
    public  ResponseEntity<DistrictDto> saveDistrict(@RequestBody DistrictDto dto){
        DistrictDto result = districtSvc.saveDistrict(dto,null);
        return new ResponseEntity<DistrictDto>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/saveDistrict/{id}", method = RequestMethod.POST   )
    public  ResponseEntity<DistrictDto> saveDistrictById(@RequestBody DistrictDto dto,@PathVariable Long id){
        DistrictDto result = districtSvc.saveDistrict(dto,id);
        return new ResponseEntity<DistrictDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchDistrict", method = RequestMethod.POST)
    public ResponseEntity<List<DistrictDto>> searchDistrict(@RequestBody DistrictSearchDto dto) {
        List<DistrictDto> result = districtSvc.searchDistrict(dto);
        return new ResponseEntity<List<DistrictDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchDistrictByIdProvince/{idProvince}", method = RequestMethod.POST)
    public ResponseEntity<List<DistrictDto>> searchDistrictByIdProvince(@PathVariable Long idProvince){
        List<DistrictDto> result = districtSvc.searchDistrictByIdProvince(idProvince);
        return new ResponseEntity<List<DistrictDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyDistrict/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyDistrict(@PathVariable Long id) {
        Boolean result = districtSvc.destroyDistrict(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);

    }

}

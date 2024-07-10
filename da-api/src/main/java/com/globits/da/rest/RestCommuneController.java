package com.globits.da.rest;

import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.search.CommuneSearchDto;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/commune")
public class RestCommuneController {

    @Autowired
    CommuneService communeSvc;

    //commune CRUD

    @RequestMapping(value = "/getCommune", method = RequestMethod.GET )
    public ResponseEntity<List<CommuneDto>> getCommune() {
        List<CommuneDto> result = communeSvc.getOnlyCommune();
        return new ResponseEntity<List<CommuneDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/saveCommune", method = RequestMethod.POST )
    public  ResponseEntity<CommuneDto> saveCommune(@RequestBody CommuneDto dto){
        CommuneDto result = communeSvc.saveCommune(dto,null);
        return new ResponseEntity<CommuneDto>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/saveCommune/{id}", method = RequestMethod.POST   )
    public  ResponseEntity<CommuneDto> saveCommune(@RequestBody CommuneDto dto,@PathVariable Long id){
        CommuneDto result = communeSvc.saveCommune(dto,id);
        return new ResponseEntity<CommuneDto>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchCommune", method = RequestMethod.POST)
    public ResponseEntity<List<CommuneDto>> searchCommune(@RequestBody CommuneSearchDto dto) {
        List<CommuneDto> result = communeSvc.searchCommune(dto);
        return new ResponseEntity<List<CommuneDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchCommuneByIdDistrict", method = RequestMethod.POST)
    public ResponseEntity<List<CommuneDto>> searchCommuneByIdDistrict(@PathVariable Long idDistrict) {
        List<CommuneDto> result = communeSvc.searchCommuneByIdDistrict(idDistrict);
        return new ResponseEntity<List<CommuneDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/destroyCommune/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroyCommune(@PathVariable Long id) {
        Boolean result = communeSvc.destroyCommune(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);

    }


}

package com.codeworld.houserenatalapi.controller;

import com.codeworld.houserenatalapi.dto.HouseDto;
import com.codeworld.houserenatalapi.entity.House;

import com.codeworld.houserenatalapi.service.HouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house/")
public class HouseController {
    private final HouseService houseService;
    @Autowired
    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(
            @RequestBody HouseDto houseDto
            ){

            HouseDto houseDto1 = houseService.addHouse(houseDto);
            return new ResponseEntity<>(houseDto1, HttpStatus.CREATED);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateHouse(
            @RequestBody HouseDto houseDto,@PathVariable Long id
    ) throws Exception {
        try{
            HouseDto updateHouse  = houseService.updateHouse(houseDto, id);
            return new ResponseEntity<>(updateHouse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to update house", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllHouse(){
        try{
            List<HouseDto> houseDtos = houseService.getHouses();
            return new ResponseEntity<>(houseDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to get Houses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getAllHouseById(
            @PathVariable Long id
    ){
        try{
            HouseDto houseDto = houseService.getHouseById(id);
            return new ResponseEntity<>(houseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to get Houses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id
    ){
        try{
            Map<String, String> response = new HashMap<>();
            response.put("Message", "House Deleted Successfully");
            houseService.deleteHouse(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<House>> pagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @RequestParam(required = false, defaultValue = "defaultValue") String field) {
        Page<House> houses = houseService.search(offset, pageSize, field);

        if (houses.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(houses);
        }
    }

}

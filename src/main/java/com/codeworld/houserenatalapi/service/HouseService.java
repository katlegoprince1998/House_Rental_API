package com.codeworld.houserenatalapi.service;

import com.codeworld.houserenatalapi.dto.HouseDto;
import com.codeworld.houserenatalapi.entity.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {
    HouseDto addHouse(HouseDto houseDto);
    HouseDto getHouseById(Long id) throws Exception;
    List<HouseDto> getHouses();
    Page<House> search(int page, int size, String field);
    HouseDto updateHouse(HouseDto houseDto, Long id) throws Exception;
    void deleteHouse(Long id) throws Exception;
}

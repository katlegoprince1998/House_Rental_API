package com.codeworld.houserenatalapi.service;

import com.codeworld.houserenatalapi.dto.HouseDto;
import com.codeworld.houserenatalapi.entity.House;
import com.codeworld.houserenatalapi.repository.HouseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService{
    private final HouseRepository houseRepository;
    @Autowired
    public HouseServiceImpl(HouseRepository houseRepository){
        this.houseRepository = houseRepository;
    }

    @Override
    public HouseDto addHouse(HouseDto houseDto) {
        House house = House
                .builder()
                .price(houseDto.getPrice())
                .bathrooms(houseDto.getBathrooms())
                .size(houseDto.getSize())
                .title(houseDto.getTitle())
                .description(houseDto.getDescription())
                .location(houseDto.getLocation())
                .build();
        houseRepository.save(house);

        BeanUtils.copyProperties(house, houseDto);
        houseDto.setId(house.getId());
        return houseDto;
    }

    @Override
    public HouseDto getHouseById(Long id) throws Exception {
        Optional<House> house = houseRepository.findById(id);
        HouseDto houseDto = new HouseDto();
        if (house.isPresent()){
            House house1 = house.get();
            BeanUtils.copyProperties(house1, houseDto);
        }else{
            throw  new Exception("Failed to get house with this id " + id);
        }
        return houseDto;
    }
    @Override
    public List<HouseDto> getHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream().map(
                this::convertToDto
        ).collect(Collectors.toList());
    }

    @Override
    public Page<House> search(int page, int size, String field) {

        if("defaultValue".equals(field)){
           return houseRepository.findAll(
                   PageRequest.of(page, size)
           );
        }
        return houseRepository.findAll(
                PageRequest.of(page, size).withSort(Sort.by(field))
        );
    }

    @Override
    public HouseDto updateHouse(HouseDto houseDto, Long id) throws Exception {
        Optional<House> existingHouseOptional = houseRepository.findById(id);

        if (existingHouseOptional.isPresent()) {
            House existingHouse = existingHouseOptional.get();

            // Update fields if they are not null in the DTO
            if (houseDto.getTitle() != null) {
                existingHouse.setTitle(houseDto.getTitle());
            }
            if (houseDto.getDescription() != null) {
                existingHouse.setDescription(houseDto.getDescription());
            }
            if (houseDto.getLocation() != null) {
                existingHouse.setLocation(houseDto.getLocation());
            }
            if (houseDto.getPrice() != 0) {
                existingHouse.setPrice(houseDto.getPrice());
            }
            if (houseDto.getBedrooms() != 0) {
                existingHouse.setBedrooms(houseDto.getBedrooms());
            }
            if (houseDto.getBathrooms() != 0) {
                existingHouse.setBathrooms(houseDto.getBathrooms());
            }
            if (houseDto.getSize() != 0) {
                existingHouse.setSize(houseDto.getSize());
            }


            // Save the updated house entity
            House updatedHouse = houseRepository.save(existingHouse);

            // Convert and return the updated house entity as HouseDto
            return convertToDto(updatedHouse);
        } else {
            throw new Exception("House with id " + id + " not found");
        }
    }

    @Override
    public void deleteHouse(Long id) throws Exception {
        Optional<House> house = houseRepository.findById(id);
        if (house.isPresent()){
            House deleteHouse = house.get();
            houseRepository.delete(deleteHouse);
        }else{
            throw new Exception("House with id \" + id + \" not found");
        }
    }

    private HouseDto convertToDto(House house) {
        HouseDto houseDto = new HouseDto();
        houseDto.setId(house.getId());
        houseDto.setTitle(house.getTitle());
        houseDto.setDescription(house.getDescription());
        houseDto.setLocation(house.getLocation());
        houseDto.setPrice(house.getPrice());
        houseDto.setBedrooms(house.getBedrooms());
        houseDto.setBathrooms(house.getBathrooms());
        houseDto.setSize(house.getSize());
        return houseDto;
    }
}

package com.codeworld.houserenatalapi.repository;

import com.codeworld.houserenatalapi.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}

package com.app.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.models.ParkingLot;

public interface ParkingRepository extends JpaRepository<ParkingLot, Integer> {

	List<ParkingLot> findByLocation(String location);
}

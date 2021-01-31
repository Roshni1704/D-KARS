package com.app.service;

import java.util.List;

import com.app.dao.models.ParkingLot;

public interface IParkingService {
	
	List<ParkingLot> fetchAllParkingLots();

	List<ParkingLot> fetchAllParkingLotsByLocation(String location);
}

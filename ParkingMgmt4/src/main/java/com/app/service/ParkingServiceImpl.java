package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.app.dao.models.Booking;
import com.app.dao.models.ParkingLot;
import com.app.dao.repository.ParkingRepository;

@Service
@Transactional
public class ParkingServiceImpl implements IParkingService {

	@Autowired
	private ParkingRepository parkingRepository;
	
		
	@Override
	public List<ParkingLot> fetchAllParkingLots() {
		
		return parkingRepository.findAll();
	}

	@Override
	public List<ParkingLot> fetchAllParkingLotsByLocation(String location) {
		
		return parkingRepository.findByLocation(location);
	}
	
	

}

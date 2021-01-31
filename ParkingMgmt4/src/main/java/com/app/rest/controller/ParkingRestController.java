package com.app.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.models.Booking;
import com.app.dao.models.ParkingLot;
import com.app.service.IBookingService;
import com.app.service.IParkingService;

@RestController
@CrossOrigin
@RequestMapping("/parkings")
public class ParkingRestController {

	@Autowired
	private IParkingService parkingService;

	@Autowired
	private IBookingService bookingService;

	public ParkingRestController() {
		System.out.println("in parking controller\n");
	}

	@GetMapping
	public ResponseEntity<?> findAllUsers() {

		List<ParkingLot> parkingLots = parkingService.fetchAllParkingLots();
		if (parkingLots.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(parkingLots, HttpStatus.OK);
	}

	@GetMapping("/search/{location}")
	public ResponseEntity<?> findUser(@PathVariable String location) {

		List<ParkingLot> parkingLots = parkingService.fetchAllParkingLotsByLocation(location);
		if (parkingLots.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(parkingLots, HttpStatus.OK);
	}

	/* to create new booking */
	@PostMapping("/booking/{uid}")
	public ResponseEntity<?> createNewBooking(@PathVariable int uid, @RequestParam int pid,
			@RequestBody @Valid Booking booking) {

		return new ResponseEntity<>(bookingService.createNewBooking(uid, pid, booking), HttpStatus.CREATED);

	}

	@GetMapping("/booking/{uid}")
	public ResponseEntity<?> checkout(@PathVariable int uid) {
		return new ResponseEntity<>(bookingService.checkout(uid), HttpStatus.OK);

	}
}

package com.app.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.models.Booking;
import com.app.dao.models.ParkingLot;
import com.app.dao.models.User;
import com.app.dao.repository.BookingRepository;
import com.app.dao.repository.CustomerRepository;
import com.app.dao.repository.ParkingRepository;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private ParkingRepository parkingRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public Booking createNewBooking(int uid, int pid, Booking booking) {
		booking.setUserId(customerRepository.findById(uid).get());
		ParkingLot parking = parkingRepository.findById(pid).get();
		
		parking.setAvailable(false);
		booking.setParkingId(parking);
		
		parkingRepository.save(parking);
		return bookingRepository.save(booking);
	}
	
	@Override
	public Booking checkout(int uid) {
		User user = customerRepository.findById(uid).get();
		Booking booking = user.getBookingId();
		ParkingLot parking = parkingRepository.findById(booking.getParkingId().getParkingId()).get();
		
		booking.setExitTime(LocalTime.now());
			
		long timeDiff = booking.getExitTime().until(booking.getEntryTime(), ChronoUnit.HOURS);
		timeDiff++;
			
		double price = calculateTotalBookingPrice(timeDiff);
		
		parking.setAvailable(true);
		booking.setParkingId(parking);
		booking.setTotalPrice(price);
		user.setBookingId(null);
		parking.setBookingId(null);
		
		customerRepository.save(user);
		parkingRepository.save(parking);
		return bookingRepository.save(booking);
	}
	
	public static double calculateTotalBookingPrice(long timeDiff) {
		double price = 0;
		if(timeDiff <= 4) {
			price += timeDiff * 10;
			timeDiff = 0;
		}else {
			price += 4 * 10;
			timeDiff -= 4;
		}
		if(timeDiff <= 4) {
			price += timeDiff * 15;
			timeDiff = 0;
		}else {
			price += 4 * 15;
			timeDiff -= 4;
		}
		if(timeDiff <= 16) {
			price += timeDiff * 20;
			timeDiff = 0;
		}else {
			price += 16 * 20;
			timeDiff -= 16;
		}
		price += timeDiff * 45;
		timeDiff = 0;
		
		return price;
	}

}

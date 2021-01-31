package com.app.service;

import com.app.dao.models.Booking;

public interface IBookingService {

	public Booking createNewBooking(int uid, int pid, Booking booking);
	
	public Booking checkout(int uid);
}

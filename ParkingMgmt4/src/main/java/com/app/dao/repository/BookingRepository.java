package com.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}

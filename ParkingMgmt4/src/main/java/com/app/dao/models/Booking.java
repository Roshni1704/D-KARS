package com.app.dao.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@JsonInclude(content = Include.NON_NULL)
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer bookingId;
	
	@Column(name = "vehicle_no", length = 20,unique = true)
	@NotBlank(message = "Vehicle No must be supplied")
	private String vehicleNo;
	
	@Column(name = "booking_date")
	//@NotBlank(message = "Booking Date must be supplied")
	private LocalDate bookingDate = LocalDate.now();
	
	@Column(name = "entry_time")
	//@NotBlank(message = "Booking Date must be supplied")
	private LocalTime entryTime = LocalTime.now();
	
	@Transient
	//@NotBlank(message = "Booking Date must be supplied")
	private LocalTime exitTime = LocalTime.now();
	
	@Column(name = "total_price",columnDefinition = "double(8,2)")
	private double totalPrice = 0;
	
//	@Column(name = "has_parked")
//	private boolean hasParked;
		
	//owning side
	@OneToOne
	@JoinColumn(name = "cust_id")
	@JsonIgnoreProperties("bookingId")
	private User userId;
	
	//owning side
	@OneToOne
	@JoinColumn(name = "parking_id")
	@JsonIgnoreProperties("bookingId")
	private ParkingLot parkingId;
}

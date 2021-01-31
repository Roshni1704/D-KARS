package com.app.dao.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "parking_lots")
@JsonInclude(content = Include.NON_NULL)
public class ParkingLot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer parkingId;
		
	@Column(length = 20)
	@NotBlank(message = "State must be supplied")
	private String state;

	@Column(length = 20)
	@NotBlank(message = "City must be supplied")
	private String city;

	@Column(length = 20)
	@NotBlank(message = "Location must be supplied")
	private String location;
	
	@Column(name = "is_available")
	private boolean isAvailable;
	
	@OneToOne(mappedBy = "parkingId",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("parkingId")
	private Booking bookingId;
	
	
}

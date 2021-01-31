package com.app.daoModels;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//OWNING SIDE
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(content = Include.NON_NULL)
public class User {

	// data members
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer custId;

	@Column(length = 20)
	@NotBlank(message = "Name must be supplied")
	private String name;

	@Column(length = 20, unique = true)
	@NotBlank(message = "Email must be supplied")
	private String email;

	@Column(length = 100)
	@NotBlank(message = "Password must be supplied")
	private String password;

	@Column(name = "phone_no", length = 10)
	@NotBlank(message = "PhoneNo must be supplied")
	private String phoneNo;
	
	@Column(length = 20)
	@NotBlank(message = "State must be supplied")
	private String state;

	@Column(length = 20)
	@NotBlank(message = "City must be supplied")
	private String city;

	@Column(length = 20)
	@NotBlank(message = "Location must be supplied")
	private String location;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@NotBlank(message = "DOB must be supplied")
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//customer wallet reference(inverse side)
	@OneToOne(mappedBy = "userId",cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private CustomerWallet customerWallet;
	
	// customer card details reference(inverse side)
	@OneToOne(mappedBy = "userId",cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private CustomerCardDetails cardDetails;
	
	//Booking reference(inverse side)
	@OneToOne(mappedBy = "userId",cascade = CascadeType.ALL)
	private Booking bookingId;


	
	
			
}

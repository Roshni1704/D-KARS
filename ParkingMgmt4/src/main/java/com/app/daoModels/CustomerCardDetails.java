package com.app.daoModels;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_details")
@JsonInclude(content = Include.NON_NULL)
public class CustomerCardDetails {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Integer cardId;
	
	@Column(name = "card_No",length = 16,unique = true)
	//@NotBlank(message = "Card No. must be supplied")
	private String cardNo;
	
	@Column(name = "name_on_card",length = 30)
	//@NotBlank(message = "Name must be supplied")
	private String name;
	
	@Column(name = "exp_date")
	//@NotBlank(message = "Expiry Date must be supplied")
	private LocalDate expDate;
		
	@Column(length = 3)
	private int cvv;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "cust_id")
	private User userId;
	
}

package com.app.dao.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "customer_wallet")
@JsonInclude(content = Include.NON_NULL)
public class CustomerWallet {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private Integer walletId;
	
	@Column(name = "wallet_balance",columnDefinition = "double(8,2)")
	//@Min(101)
	private double walletBalance;
	
//	@Column(name = "has_subcribed")
//	private boolean hasSubscribed;

	@OneToOne
	@MapsId
	@JoinColumn(name = "cust_id")
	@JsonIgnoreProperties("customerWallet")
	private User userId;

//	public CustomerWallet(int id,double walletBalance) {
//		super();
//		walletId = id;
//		this.walletBalance = walletBalance;
//}
	
	
}

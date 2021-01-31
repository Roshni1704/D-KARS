package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dao.models.Booking;
import com.app.dao.models.CustomerCardDetails;
import com.app.dao.models.User;

public interface ICustomerService {

	//save Customer Details
	User saveCustomerDetails(User user);
	
	//save Customer Booking Details
	User saveCustomerBookingDetails(User user,Booking b);
	
	//list All Customers
	List<User> listAllCustomers();
	
	//add Card Details
	User addCardDetails(User user,CustomerCardDetails cardDetails);
	
	//get Customer By Id
	User getCustomerById(int userId);
	
	
	//removeCardDetails
	String removeCardDetails(User user);
	
	//add Customer Wallet Balance
	User addCustomerWalletBalance(User user,double amount);
	
	//find customer by name
	User findByName(String customerName);
	
	//to substract balance
	User subCustomerWalletBalance(User user, double bill);
}

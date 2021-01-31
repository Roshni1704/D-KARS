package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.models.Booking;
import com.app.dao.models.CustomerCardDetails;
import com.app.dao.models.CustomerWallet;
import com.app.dao.models.Role;
import com.app.dao.models.User;
import com.app.dao.repository.CustomerCardDetailsRespoitory;
import com.app.dao.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	// D.I.
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerCardDetailsRespoitory customerCardDetailsRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// save customer details
	@Override
	public User saveCustomerDetails(User user) {
		String password = user.getPassword();
		String encryptedPwd = passwordEncoder.encode(password);
		user.setPassword(encryptedPwd);

		user.setRole(Role.CUSTOMER);

		CustomerWallet wallet = new CustomerWallet(user.getCustId(), 0.0, user);
		user.setCustomerWallet(wallet);

		return customerRepository.save(user);
	}

	// save Customer Booking Details
	@Override
	public User saveCustomerBookingDetails(User user, Booking booking) {
		user.setBookingId(booking); // setting child ref in parent
		booking.setUserId(user); // setting parent ref in child
		return customerRepository.save(user); // saving parent ref which saves child ref as well
	}

	// get all customer details
	@Override
	public List<User> listAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public User addCardDetails(User user, CustomerCardDetails cardDetails) {
		user.setCardDetails(cardDetails);
		cardDetails.setUserId(user);
		return customerRepository.save(user);
	}

	@Override
	public User getCustomerById(int userId) {
		
		Optional<User> customer = customerRepository.findById(userId);
		User existingCustomer = null;
		if(customer.isPresent())
			existingCustomer = customer.get();//handle exception over here if existingCust is null
		return existingCustomer;
	}

	@Override
	public String removeCardDetails(User user) {

		customerCardDetailsRepository.deleteById(user.getCardDetails().getCardId());
		user.setCardDetails(null);
		customerRepository.save(user);

		return "Card Details deleted successfully";
	}

	@Override
	public User addCustomerWalletBalance(User user, double amount) {
		CustomerWallet wallet = user.getCustomerWallet();
		wallet.setWalletBalance(amount + wallet.getWalletBalance());
		return customerRepository.save(user);

	}

	@Override
	public User subCustomerWalletBalance(User user, double bill) {
		CustomerWallet wallet = user.getCustomerWallet();
		if (wallet.getWalletBalance() - bill < 0) {
			System.out.println("Insufficient balance");
			// either throw exception or return null
			return null;
		} else {
			wallet.setWalletBalance(wallet.getWalletBalance() - bill);
			return customerRepository.save(user);
		}

	}

	@Override
	public User findByName(String customerName) {
	return customerRepository.findByName(customerName);
	}

}

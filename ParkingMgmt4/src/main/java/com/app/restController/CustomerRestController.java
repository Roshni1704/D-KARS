package com.app.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.daoModels.Booking;
import com.app.daoModels.CustomerCardDetails;
import com.app.daoModels.CustomerWallet;
import com.app.daoModels.User;
import com.app.service.ICustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class CustomerRestController {

	// D.I
	@Autowired
	private ICustomerService customerService;

	public CustomerRestController() {
		System.out.println("in customer controller\n");
	}

	/* get all customer details */
	@GetMapping
	public ResponseEntity<?> getAllCustomerDetails() {
		System.out.println("in get all customers\n");

		// invoke service layer method
		List<User> users = customerService.listAllCustomers();
		if (users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/* to create new resource :-Customer(UNMARSHALLING) */
	@PostMapping
	public ResponseEntity<?> createNewCustomerRecord(@RequestBody @Valid User user) {
		System.out.println("in save customer " + user);// transient

		User details = customerService.saveCustomerDetails(user);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}

	/* to create new booking */
	@PostMapping("/booking")
	public ResponseEntity<?> createNewBooking(@RequestBody User user,@RequestBody @Valid Booking booking) {
		System.out.println("in save customer booking " + booking);// transient

		User details = customerService.saveCustomerBookingDetails(user, booking);
		return new ResponseEntity<>(details, HttpStatus.CREATED);

	}
	
	/*cancel a booking*/
	
	
	/* add card details*/
	@PostMapping("/card/{uid}")
	public ResponseEntity<?> addCustomerCardDetails(@PathVariable int uid , @RequestBody @Valid CustomerCardDetails cardDetails) {
		System.out.println("in add card details " + cardDetails);// transient

		Optional<User> customer = customerService.getCustomerById(uid);
		if(customer.isPresent())
		{
			User existingCustomer = customer.get();
			existingCustomer = customerService.addCardDetails(existingCustomer, cardDetails);
		}
		return new ResponseEntity<>(customer, HttpStatus.CREATED);

	}
	
	/*remove card details*/
	@DeleteMapping("/card/{uid}")
	public ResponseEntity<?> deleteCustomerCardDetails(@PathVariable int uid) {
		System.out.println("in delete card details \n");// transient

		Optional<User> customer = customerService.getCustomerById(uid);
		String msg = null;
		if (customer.isPresent()) { 
			User existingCustomer = customer.get();
			msg = customerService.removeCardDetails(existingCustomer);
		}
		return new ResponseEntity<>(msg, HttpStatus.CREATED);

	}
	
	/* add money to wallet by customer*/
	@PostMapping("/wallet/{uid}")
	public ResponseEntity<?> addWalletMoney(@PathVariable int uid,@RequestParam double amount) {
		System.out.println("in add Wallet Money \n");// transient

		Optional<User> customer = customerService.getCustomerById(uid);
		double walletBalance = 0.0;
		if (customer.isPresent()) { 
			User existingCustomer = customer.get();
			walletBalance = customerService.addCustomerWalletBalance(existingCustomer,amount).getCustomerWallet().getWalletBalance();
		}
		return new ResponseEntity<>(walletBalance, HttpStatus.CREATED);

	} 
	
	
	/*update wallet balance by admin*/
	
	
	/**/

}

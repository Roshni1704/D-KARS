package com.app.rest.controller;

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

import com.app.dao.models.Booking;
import com.app.dao.models.CustomerCardDetails;
import com.app.dao.models.User;
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

	@GetMapping("/{cust_id}")
	public ResponseEntity<?> getCustomerByIDPathVar(@PathVariable(name = "cust_id") int cid) {
		System.out.println("in get customer by id using path var..." + cid);
		return new ResponseEntity<>(customerService.getCustomerById(cid), HttpStatus.OK);
	}


	/* add card details */
	@PostMapping("/card/{uid}")
	public ResponseEntity<?> addCustomerCardDetails(@PathVariable int uid,
			@RequestBody @Valid CustomerCardDetails cardDetails) {
		System.out.println("in add card details " + cardDetails);// transient

		User existingCustomer = customerService.addCardDetails(customerService.getCustomerById(uid), cardDetails);

		return new ResponseEntity<>(existingCustomer, HttpStatus.CREATED);

	}

	/* remove card details */
	@DeleteMapping("/card/{uid}")
	public ResponseEntity<?> deleteCustomerCardDetails(@PathVariable int uid) {
		System.out.println("in delete card details \n");// transient

		String msg = customerService.removeCardDetails(customerService.getCustomerById(uid));

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	/* add money to wallet by customer */
	@PostMapping("/wallet/{uid}")
	public ResponseEntity<?> addOrUpdateWalletMoney(@PathVariable int uid, @RequestParam double amount) {
		System.out.println("in add Wallet Money \n");// transient

		double walletBalance = customerService.addCustomerWalletBalance(customerService.getCustomerById(uid), amount)
				.getCustomerWallet().getWalletBalance();

		return new ResponseEntity<>(walletBalance, HttpStatus.CREATED);

	}

	/* subtract money from wallet */
	@PostMapping("/bill/{uid}")
	public ResponseEntity<?> subWalletMoney(@PathVariable int uid, @RequestParam double amount) {
		System.out.println("in add Wallet Money \n");// transient

		double walletBalance = customerService.subCustomerWalletBalance(customerService.getCustomerById(uid), amount)
				.getCustomerWallet().getWalletBalance();

		return new ResponseEntity<>(walletBalance, HttpStatus.CREATED);

	}

}

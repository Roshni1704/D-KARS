package com.app.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.customException.ParkingManagementException;
import com.app.dto.ParkingError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ParkingManagementException.class)
	public ResponseEntity<ParkingError> handleMappingException(ParkingManagementException ex) {
		ParkingError error = new ParkingError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ParkingError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

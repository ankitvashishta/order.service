package com.dbs.order.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dbs.order.service.exception.ItemNotFoundException;
import com.netflix.hystrix.exception.HystrixRuntimeException;

@RestControllerAdvice
public class OrderServiceExceptionHandler {

	@ExceptionHandler(value = HystrixRuntimeException.class)
	public ResponseEntity<Object> exception(HystrixRuntimeException exception) {
		return new ResponseEntity<>("Item Service not available.", HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(value = ItemNotFoundException.class)
	public ResponseEntity<Object> handleGenericRuntimeException(ItemNotFoundException e) {
		return new ResponseEntity<>("ITEM_NOT_FOUND", HttpStatus.NOT_FOUND);
	}
	
}

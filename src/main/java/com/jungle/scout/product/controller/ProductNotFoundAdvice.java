package com.jungle.scout.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductNotFoundAdvice {
	@ResponseBody //signals that this advice is rendered straight into the response body
	@ExceptionHandler(ProductNotFoundException.class) //only respond if this is the exception thrown
	@ResponseStatus(HttpStatus.NOT_FOUND) //issue a 404
	String productNotFoundHandler(ProductNotFoundException exception){
		return exception.getMessage();
	}

}

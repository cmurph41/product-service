package com.jungle.scout.product;

public class ProductNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProductNotFoundException(String id) {
		super("could not find product " + id);
	}
}

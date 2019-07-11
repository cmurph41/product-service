package com.jungle.scout.product;

public class ProductNotFoundException extends RuntimeException {
	ProductNotFoundException(String id) {
		super("could not find product " + id);
	}
}

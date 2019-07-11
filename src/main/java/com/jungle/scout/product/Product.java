package com.jungle.scout.product;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data //Lombok annotation to create all getters, setters, equals, hash, toString
@Entity //JPA annotation to make this obj ready for storage in a JPA-based data store
public class Product {
	
	
	@Id
	private String ASIN;
	private String category;
	private Integer rank;

	@ElementCollection(targetClass=Double.class)
	private List<Double> dimensions;

	Product() {}
	Product (String ASIN, String category, Integer rank, List<Double> dimensions) {
		this.ASIN = ASIN;
		this.category = category;
		this.rank = rank;
		this.dimensions = dimensions;
	}
}




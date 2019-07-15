package com.jungle.scout.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data //Lombok annotation to create all getters, setters, equals, hash, toString
@Entity //JPA annotation to make this obj ready for storage in a JPA-based data store
@Table(name="amz_products")
public class AmzProduct {
	
	@Id
	private String ASIN;
	private String category;
	private Integer rank;
	private String dimensions;

	AmzProduct() {}
	AmzProduct (String ASIN, String category, Integer rank,String dimensions) {
		this.ASIN = ASIN;
		this.category = category;
		this.rank = rank;
		this.dimensions = dimensions;
	}
}




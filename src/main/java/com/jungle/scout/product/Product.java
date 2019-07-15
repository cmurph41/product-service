package com.jungle.scout.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data //Lombok annotation to create all getters, setters, equals, hash, toString
@Entity //JPA annotation to make this obj ready for storage in a JPA-based data store
@Table(name="js_amz_products")
public class Product {
	
	
	@Id
	private String ASIN;
	private String category;
	private Integer rank;
	private String dimensions;
	
//	@Type(type="double-array")
//	@Column(
//			name="dimensions",
//			columnDefinition = "double[]"
//	)
//	


	Product() {}
	Product (String ASIN, String category, Integer rank,String dimensions) {
		this.ASIN = ASIN;
		this.category = category;
		this.rank = rank;
		this.dimensions = dimensions;
	}
}




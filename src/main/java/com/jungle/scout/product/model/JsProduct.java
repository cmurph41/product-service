package com.jungle.scout.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data //Lombok annotation to create all getters, setters, equals, hash, toString
@Entity //JPA annotation to make this obj ready for storage in a JPA-based data store
@Table(name="js_amz_products", schema="public")
public class JsProduct  {
	
	@Id
	private String ASIN;
	private String category;
	private Integer rank;
	private String dimensions;

	JsProduct() {}
	public JsProduct (String ASIN, String category, Integer rank,String dimensions) {
		this.ASIN = ASIN;
		this.category = category;
		this.rank = rank;
		this.dimensions = dimensions;
	}
	public JsProduct orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}




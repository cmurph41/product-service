package com.jungle.scout.product;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

 
//indicates that the data returned by each 
//method will be written straight into the response body 
//instead of rendering a template
@RestController
public class ProductController {

	private final ProductRepository productRepo;
	
	ProductController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	//Aggregate root
	@GetMapping("/products") 
	List<Product> all() {
		return productRepo.findAll();
	}
	
	@PostMapping("/products") 
	Product newProduct(@RequestBody Product newProduct) {
		return productRepo.save(newProduct);
	}
	
	//Single Item
	@GetMapping("/products/{id}") 
	Product one(@PathVariable String id){
		return productRepo.findById(id)
				.orElseThrow(()-> new ProductNotFoundException(id));
	}
	
	@PutMapping("products/{id}")
	Product replaceProduct(@RequestBody Product newProduct, @PathVariable String id)
	{
		return productRepo.findById(id)
				.map(product -> {
					product.setCategory(newProduct.getCategory());
					product.setRank(newProduct.getRank());
					product.setDimension(newProduct.getDimension());
					return productRepo.save(product);

				}).orElseGet(()->{
					newProduct.setASIN(id);
					return productRepo.save(newProduct);
				});
	}
	
	@DeleteMapping("products/{id}")
	void deleteProduct(@PathVariable String id) {
		productRepo.deleteById(id);
	}
	
}

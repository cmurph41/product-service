package com.jungle.scout.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jungle.scout.product.model.AmzProduct;
import com.jungle.scout.product.model.AmzProductRepository;
import com.jungle.scout.product.model.JsProduct;
import com.jungle.scout.product.model.JsProductRepository;

 
//indicates that the data returned by each 
//method will be written straight into the response body 
//instead of rendering a template
@RestController
public class ProductController {

	private final AmzProductRepository amzProductRepo;
	private final JsProductRepository jsProductRepo;
	
	ProductController(AmzProductRepository amzProductRepo, JsProductRepository jsProductRepo) {
		this.amzProductRepo = amzProductRepo;
		this.jsProductRepo = jsProductRepo;

	}
	
	//Aggregate root
	@CrossOrigin//(origins = "http://cmurph41-jungle-scout.s3-website.us-east-2.amazonaws.com", maxAge = 3600)
	@GetMapping("/js-products") 
	List<JsProduct> jsAll() {
		return jsProductRepo.findAll();
	}
	//Aggregate root
	@CrossOrigin//(origins = "http://cmurph41-jungle-scout.s3-website.us-east-2.amazonaws.com", maxAge = 3600)
	@GetMapping("/amz-products") 
	List<AmzProduct> amzAll() {
		return amzProductRepo.findAll();
	}
	
	@PostMapping("/js-products") 
	JsProduct newProduct(@RequestBody JsProduct newProduct) {
		return jsProductRepo.save(newProduct);
	}
	
	@CrossOrigin//(origins = "http://cmurph41-jungle-scout.s3-website.us-east-2.amazonaws.com", maxAge = 3600)
	@GetMapping("/js-products/{id}") 
	JsProduct jsOne(@PathVariable String id){
		Optional<JsProduct> jsProduct = jsProductRepo.findById(id);
		
		if(!jsProduct.isPresent()) {
			Optional<AmzProduct> amzProduct = amzProductRepo.findById(id);
			if(amzProduct.isPresent()) {
				JsProduct newJsProduct = new JsProduct(
						amzProduct.get().getASIN(), 
						amzProduct.get().getCategory(), 
						amzProduct.get().getRank(),
						amzProduct.get().getDimensions());
				jsProductRepo.save(newJsProduct);
				return newJsProduct;
			}
		}
		return jsProduct.orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	//Single Item
	@CrossOrigin//(origins = "http://cmurph41-jungle-scout.s3-website.us-east-2.amazonaws.com", maxAge = 3600)
	@GetMapping("/amz-products/{id}") 
	AmzProduct amzOne(@PathVariable String id){
		return amzProductRepo.findById(id)
				.orElseThrow(()-> new ProductNotFoundException(id));
	}
	
	@DeleteMapping("products/{id}")
	void deleteProduct(@PathVariable String id) {
		jsProductRepo.deleteById(id);
	}
	
}

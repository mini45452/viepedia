package com.viepedialearn2.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viepedialearn2.productservice.repository.ProductRepository;
import com.viepedialearn2.productservice.model.Product;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/")
	public List<Product> getAllProduct() {
		return this.productRepository.findAll();
	}
	
    // Create a new product
    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Update an existing product by ID
    @PutMapping("/{id}/")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}/")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }
    
    // Check if you can buy a product based on available stock
    @GetMapping("/{id}/canBuy")
    public boolean canBuyProduct(@PathVariable int id, @RequestParam int amount) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            return product.getStock() >= amount;
        } else {
            // Product with the given ID doesn't exist
            return false;
        }
    }
}
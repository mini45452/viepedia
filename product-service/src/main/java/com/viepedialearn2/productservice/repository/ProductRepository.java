package com.viepedialearn2.productservice.repository;

import com.viepedialearn2.productservice.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

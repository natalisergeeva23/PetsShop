package com.example.springmodels.repository;

import com.example.springmodels.models.Product;
import com.example.springmodels.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByNameProduct(String nameProduct);


}

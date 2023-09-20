package com.example.springmodels.repository;

import com.example.springmodels.models.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeProductRepository extends JpaRepository<TypeProduct, Long> {
    TypeProduct findByNameTypeProduct(String nameTypeProduct);
}

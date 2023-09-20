package com.example.springmodels.repository;

import com.example.springmodels.models.Store;
import com.example.springmodels.models.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByNameStore(String nameStore);

}

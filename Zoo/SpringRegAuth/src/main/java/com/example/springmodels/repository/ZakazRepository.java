package com.example.springmodels.repository;

import com.example.springmodels.models.Zakaz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZakazRepository extends JpaRepository<Zakaz, Long> {

}

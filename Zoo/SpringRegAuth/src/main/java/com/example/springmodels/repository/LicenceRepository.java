package com.example.springmodels.repository;

import com.example.springmodels.models.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenceRepository extends JpaRepository<Licence, Long> {
}

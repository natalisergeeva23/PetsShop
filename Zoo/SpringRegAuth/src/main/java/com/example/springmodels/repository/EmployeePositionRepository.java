package com.example.springmodels.repository;

import com.example.springmodels.models.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
    EmployeePosition findByNameEmployeePosition(String nameEmployeePosition);
}


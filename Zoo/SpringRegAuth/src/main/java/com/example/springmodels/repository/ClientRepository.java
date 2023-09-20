package com.example.springmodels.repository;

import com.example.springmodels.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByFirstNameClient(String firstNameClient);
}

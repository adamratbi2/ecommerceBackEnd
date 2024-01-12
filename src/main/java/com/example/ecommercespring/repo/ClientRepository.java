package com.example.ecommercespring.repo;

import com.example.ecommercespring.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    Optional<Client> findByUsername(String username);
}

package com.example.BcryptTwo.repository;

import com.example.BcryptTwo.model.cust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<cust, Long> {

    cust findByUsername(String username);
}

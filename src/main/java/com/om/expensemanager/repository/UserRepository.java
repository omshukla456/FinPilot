package com.om.expensemanager.repository;

import com.om.expensemanager.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<user, Long> {

    Optional<user> findByEmail(String email);
}
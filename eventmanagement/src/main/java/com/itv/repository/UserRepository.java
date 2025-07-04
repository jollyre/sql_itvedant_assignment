package com.itv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}

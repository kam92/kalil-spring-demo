package com.kalilcamera.backend.repository;

import java.util.Optional;

import com.kalilcamera.backend.entity.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String name);
    Optional<UserAccount> findByUsername(String name);
    Optional<UserAccount> findByEmail(String email);
     Optional<UserAccount> findById(Long id);
    
}

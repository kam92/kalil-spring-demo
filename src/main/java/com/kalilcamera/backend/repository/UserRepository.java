package com.kalilcamera.backend.repository;

import com.kalilcamera.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String name);
    boolean existsById(Long id);
    Optional<UserEntity> findByUsername(String name);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(Long id);
}

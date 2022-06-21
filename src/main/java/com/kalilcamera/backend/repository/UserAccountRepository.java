package com.kalilcamera.backend.repository;

import java.util.Optional;

import com.kalilcamera.backend.dto.UserGetDto;
import com.kalilcamera.backend.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String name);

    boolean existsById(Long id);
    Optional<UserGetDto> findByUsername(String name);
    Optional<UserGetDto> findByEmail(String email);
     Optional<UserEntity> findById(Long id);
    
}

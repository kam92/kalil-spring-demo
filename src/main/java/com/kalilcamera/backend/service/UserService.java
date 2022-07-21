package com.kalilcamera.backend.service;

import com.kalilcamera.backend.entity.UserEntity;
import com.kalilcamera.backend.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userAccountRepo;

    public List<UserEntity> getUserList() {
        return userAccountRepo.findAll();
    }

    public ResponseEntity<?> save(UserEntity user) {
        try {
            verifyIfUserExists(user);
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(user.getUsername());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userEntity.setActive(false);
            userEntity.setRoleId(0);
            userEntity.setCreatedAt(LocalDateTime.now());
            userAccountRepo.save(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Usuario e email devem ser únicos.",HttpStatus.CONFLICT);
        }
    }

    public Optional<UserEntity> edit( Long id, UserEntity user) {
            try {
                return userAccountRepo.findById(id)
                        .map(userEntity -> {
                            userEntity.setUsername(user.getUsername());
                            userEntity.setEmail(user.getEmail());
                            userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                            return userAccountRepo.save(userEntity);
                        });
            } catch (Exception e) {
                return Optional.empty();
            }
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userAccountRepo.findByEmail(email);
    }
    public Optional<UserEntity> findByUsername(String name) {
        return userAccountRepo.findByUsername(name);
    }
    public boolean emailExist(String email) {
        return userAccountRepo.existsByEmail(email);
    }
    public boolean usernameExist(String name) {
        return userAccountRepo.existsByUsername(name);
    }
    public void trimFields(@NotNull UserEntity user) {
        user.setEmail(user.getEmail().trim());
        user.setUsername(user.getUsername().trim());
    }

    public void validateFields(String name, String email) {
        if (!usernameExist(name)) {
            emailExist(email);
        }
    }
    public void verifyIfUserExists(UserEntity userEntity)  {
        trimFields(userEntity);
        validateFields(userEntity.getUsername(), userEntity.getEmail());
    }

    public Optional<UserEntity> delete(Long id) {
        return userAccountRepo.deleteById(id);

    }
}

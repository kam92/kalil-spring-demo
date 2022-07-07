package com.kalilcamera.backend.service;

import com.kalilcamera.backend.dto.UserGetDto;
import com.kalilcamera.backend.dto.UserPostDto;
import com.kalilcamera.backend.entity.UserEntity;
import com.kalilcamera.backend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepo;

    public List<UserGetDto> getUserDtoList() {
        List< UserEntity> userEntityList = userAccountRepo.findAll();
        List<UserGetDto> userGetDtoList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList) {
            UserGetDto userGetDto = new UserGetDto();
            userGetDto.setEmail(userEntity.getEmail());
            userGetDto.setUsername(userEntity.getUsername());
            userGetDto.setActive(userEntity.isActive());
            userGetDto.setRoleId(userEntity.getRoleId());
            userGetDtoList.add(userGetDto);
        }
        return userGetDtoList;
    }

    public ResponseEntity<?> save(UserPostDto userPostDto) {
        try {
            verifyIfUserExists(userPostDto);
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userPostDto.getUsername());
            userEntity.setEmail(userPostDto.getEmail());
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userPostDto.getPassword()));
            userEntity.setActive(false);
            userEntity.setRoleId(0);
            userAccountRepo.save(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> edit( Long id, UserPostDto userPostDto) {
                try {
                    verifyIfUserExists(userPostDto);
                    UserEntity userEntityAlteracao = userAccountRepo.findById(id).get();
                    userEntityAlteracao.setEmail(userPostDto.getEmail());
                    userEntityAlteracao.setPassword(new BCryptPasswordEncoder().encode(userPostDto.getPassword()));
                    userEntityAlteracao.setUsername(userPostDto.getUsername());
                    userAccountRepo.save(userEntityAlteracao);
                    return new ResponseEntity<>("changed data for user id "+userEntityAlteracao.getId().toString()+".", HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
    }

    public Optional<UserGetDto> findByEmail(String email) {
        return userAccountRepo.findByEmail(email);
    }

    public Optional<UserGetDto> findByUsername(String name) {
        return userAccountRepo.findByUsername(name);
    }

    public boolean emailExist(String email) {
        return userAccountRepo.existsByEmail(email);
    }

    public boolean usernameExist(String name) {
        return userAccountRepo.existsByUsername(name);
    }
    public void trimFields(UserPostDto userPostDto) {
        userPostDto.setEmail(userPostDto.getEmail().trim());
        userPostDto.setUsername(userPostDto.getUsername().trim());
    }

    public boolean validateFields(UserPostDto userPostDto) {
        if (usernameExist(userPostDto.getUsername())) {
            return false;
        }
        return !emailExist(userPostDto.getEmail());
    }
    public void verifyIfUserExists(UserPostDto userPostDto) throws Exception {
        trimFields(userPostDto);
        if (!validateFields(userPostDto)) {
            throw new Exception();
        }
    }
}

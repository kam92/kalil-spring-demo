package com.kalilcamera.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kalilcamera.backend.dto.UserGetDto;
import com.kalilcamera.backend.dto.UserPostDto;
import com.kalilcamera.backend.entity.UserEntity;
import com.kalilcamera.backend.repository.UserAccountRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepo;

    public List<UserGetDto> getUserEntityList() {
        return getUserDtoList();
    }

    public ArrayList<UserGetDto> getUserDtoList() {
        List< UserEntity> userEntityList = userAccountRepo.findAll();
       ArrayList<UserGetDto> userGetDtoList = new ArrayList<>();

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
            if (usernameExist(userPostDto.getUsername().trim())) {
                throw new Exception();
            }

            if (emailExist(userPostDto.getEmail().trim())) {
                throw new Exception();
            }

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(userPostDto.getUsername().trim());
            userEntity.setEmail(userPostDto.getEmail().trim());
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userPostDto.getPassword()));
            userEntity.setActive(false);
            userEntity.setRoleId(0);
            userAccountRepo.save(userEntity);
            return new ResponseEntity<>("CADASTRADO", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> edit(UserPostDto userPostDto, Long id) {

        if (userAccountRepo.existsById(id)) {
                UserEntity userEntityAlteracao = userAccountRepo.findById(id).get();
                try {
                    if (usernameExist(userPostDto.getUsername().trim())) {
                        throw new Exception();
                    }

                    if (emailExist(userPostDto.getEmail().trim())) {
                        throw new Exception();
                    }

                    userEntityAlteracao.setEmail(userPostDto.getEmail().trim());
                    userEntityAlteracao.setPassword(new BCryptPasswordEncoder().encode(userPostDto.getPassword()));
                    userEntityAlteracao.setUsername(userPostDto.getUsername().trim());
                    userAccountRepo.save(userEntityAlteracao);
                    return new ResponseEntity<>("ALTERADO", HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
}

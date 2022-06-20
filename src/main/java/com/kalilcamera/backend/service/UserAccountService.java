package com.kalilcamera.backend.service;

import java.util.List;
import java.util.Optional;

import com.kalilcamera.backend.entity.UserAccount;
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

    public List<UserAccount> getList() {
        return userAccountRepo.findAll();
    }

    public ResponseEntity<?> save(UserAccount usuario) {
        try {
            if (usernameExist(usuario.getUsername().trim())) {
                throw new Exception();
            }

            if (emailExist(usuario.getEmail().trim())) {
                throw new Exception();
            }

            usuario.setUsername(usuario.getUsername().trim());
            usuario.setEmail(usuario.getEmail().trim());
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            usuario.setActive(false);
            usuario.setRoleId(0);
            userAccountRepo.save(usuario);
            return new ResponseEntity<>("CADASTRADO", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> edit(UserAccount usuario, Long id) {

            if (userAccountRepo.findById(id).isPresent()) {
                UserAccount userAlteracao;
                userAlteracao = userAccountRepo.findById(id).get();
                try {
                    if (usernameExist(usuario.getUsername().trim())) {
                        throw new Exception();
                    }

                    if (emailExist(usuario.getEmail().trim())) {
                        throw new Exception();
                    }

                    userAlteracao.setEmail(usuario.getEmail().trim());
                    userAlteracao.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
                    userAlteracao.setUsername(usuario.getUsername().trim());
                    userAccountRepo.save(userAlteracao);
                    return new ResponseEntity<>("ALTERADO", HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Optional<UserAccount> findByEmail(String email) {
        return userAccountRepo.findByEmail(email);
    }

    public Optional<UserAccount> findByUsername(String name) {
        return userAccountRepo.findByUsername(name);
    }

    public boolean emailExist(String email) {
        return userAccountRepo.existsByEmail(email);
    }

    public boolean usernameExist(String name) {
        return userAccountRepo.existsByUsername(name);
    }
}

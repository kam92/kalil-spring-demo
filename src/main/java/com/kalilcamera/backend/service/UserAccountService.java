package com.kalilcamera.backend.service;

import java.util.List;
import java.util.Optional;

import com.kalilcamera.backend.entity.UserAccount;
import com.kalilcamera.backend.repository.UserRepository;
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
    private UserRepository userAccountRepo;

    public List<UserAccount> getList() {
        return userAccountRepo.findAll();
    }

    public ResponseEntity<?> save(UserAccount usuario) {
        try {
            validarDados(usuario);
            usuario.setActive(false);
            usuario.setRoleId(0);
            userAccountRepo.save(usuario);
            return new ResponseEntity<>("CADASTRADO", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void validarDados(UserAccount userAccount) {
        userAccount.setUsername(userAccount.getUsername().trim());
        userAccount.setEmail(userAccount.getEmail().trim());
        encriptarSenha(userAccount);
    }

    public void encriptarSenha(UserAccount userAccount) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
    }

    public String encriptarEdevolverSenha(UserAccount userAccount) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(userAccount.getPassword());
    }

    public Optional<UserAccount> getUsuario(Integer id) {
        return userAccountRepo.findById(id);
    }

    public void deleteUsuario(Integer id) {
        userAccountRepo.deleteById(id);
    }

    public ResponseEntity<?> edit(UserAccount usuario, Integer id) {
        
            UserAccount userAlteracao;
            if (userAccountRepo.findById(id).isPresent()) {
                userAlteracao = userAccountRepo.findById(id).get();
                try {
                    userAlteracao.setEmail(usuario.getEmail());
                    userAlteracao.setPassword(encriptarEdevolverSenha(usuario));
                    userAlteracao.setUsername(usuario.getUsername());
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

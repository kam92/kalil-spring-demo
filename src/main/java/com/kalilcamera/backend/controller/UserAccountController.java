package com.kalilcamera.backend.controller;

import com.kalilcamera.backend.dto.UserPostDto;
import com.kalilcamera.backend.repository.UserAccountRepository;
import com.kalilcamera.backend.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository usuarioRepository;

    @GetMapping("/lista")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(userAccountService.getUserEntityList(), HttpStatus.OK);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> createUserAccount(@RequestBody UserPostDto usuario) {
        return userAccountService.save(usuario);
    }
    
    @PutMapping("/alterar")
    public ResponseEntity<?> editUserAccount(@RequestBody UserPostDto usuario, @RequestParam Long id) {
       return userAccountService.edit(usuario, id);
    }

}

package com.kalilcamera.backend.controller;

import com.kalilcamera.backend.dto.UserPostDto;
import com.kalilcamera.backend.entity.UserEntity;
import com.kalilcamera.backend.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Falha de autenticação."),
        @ApiResponse(responseCode = "200", description = "Sucesso.")
})
public class UserController {
    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar usuário",description = "Insere um novo usuário no sistema.")
    @ApiResponse(responseCode = "201", description = "Cadastrado.")
    public ResponseEntity<?> createUserAccount(@RequestBody UserEntity usuario) {
        return userAccountService.save(usuario);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar usuários",description = "Exibe uma lista com todos os usuários cadastrados.")
    public ResponseEntity<?> listAllUserAccount() {
        return new ResponseEntity<>(userAccountService.getUserList(), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    @Operation(summary = "Alterar dados de usuário por ID.", description =  "Somente username, password e email podem ser alterados.")
    public Optional<UserEntity> editUserAccount(@Parameter(description = "ID do usuário a ser alterado") @RequestParam Long id, @RequestBody UserEntity usuario) {
       return userAccountService.edit(id, usuario);
    }

}

package com.kalilcamera.backend.controller;

import com.kalilcamera.backend.dto.UserPostDto;
import com.kalilcamera.backend.repository.UserAccountRepository;
import com.kalilcamera.backend.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Falha de autenticação."),
        @ApiResponse(responseCode = "200", description = "Sucesso.")
})
public class UserAccountController {
    @Autowired
    UserAccountService userAccountService;


    @PostMapping("/cadastro")
    @Operation(summary = "Cadastrar usuário",description = "Insere um novo usuário no sistema.")
    @ApiResponse(responseCode = "201", description = "Cadastrado.")
    public ResponseEntity<?> createUserAccount(@RequestBody UserPostDto usuario) {
        return userAccountService.save(usuario);
    }

    @GetMapping("/lista")
    @Operation(summary = "Listar usuários",description = "Exibe uma lista com todos os usuários cadastrados.")
    public ResponseEntity<?> listAllUserAccount() {
        return new ResponseEntity<>(userAccountService.getUserDtoList(), HttpStatus.OK);
    }

    @PutMapping("/alterar")
    @Operation(summary = "Alterar dados de usuário por ID.", description =  "Somente username, password e email podem ser alterados.")
    public ResponseEntity<?> editUserAccount(@Parameter(description = "ID do usuário a ser alterado") @RequestParam Long id, @RequestBody UserPostDto usuario) {
       return userAccountService.edit(id, usuario);
    }

}

package com.kalilcamera.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;
}

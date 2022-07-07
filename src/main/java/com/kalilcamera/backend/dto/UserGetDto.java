package com.kalilcamera.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NotNull
public class UserGetDto {

    private String username;
    private String email;
    private Integer roleId;
    private boolean active;

}

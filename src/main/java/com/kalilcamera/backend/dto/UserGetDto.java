package com.kalilcamera.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserGetDto {

    private String username;
    private String email;
    private Integer roleId;
    private boolean active;

}

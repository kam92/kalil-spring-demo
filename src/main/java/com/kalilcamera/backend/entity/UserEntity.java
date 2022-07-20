package com.kalilcamera.backend.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @NotNull
    private String username;

    @Column(unique = true, nullable = false)
    @Email
    @NotNull
    private String email;

    @Column(name="pw", nullable = false)
    @NotNull
    private String password;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;

    @Column
    private Integer roleId;

    @Column
    private LocalDateTime createdAt;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    private LocalDateTime updatedAt;
} 
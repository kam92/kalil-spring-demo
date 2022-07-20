package com.kalilcamera.backend.repository;

import com.kalilcamera.backend.entity.UserEntity;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserMapper implements RowMapper<UserEntity> {

    public UserEntity mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return UserEntity.builder()
                .active(rs.getBoolean("active"))
                .email(rs.getString("email"))
                .username(rs.getString("username"))
                .id(rs.getLong("id"))
                .password(" ")
                .username(rs.getString("user"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .build();
    }
}

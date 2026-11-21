package com.example.newgateway.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "users")
public class User {
    @Id
    private Long id;
    @Column("email")
    private String email;
    @Column("password")
    private String password;

    @Column("active")
    private boolean active;
}

package com.Antoine.Jerry.note_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    private String user_id;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;
}

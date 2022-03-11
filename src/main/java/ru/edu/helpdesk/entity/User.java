package ru.edu.helpdesk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.ORDINAL)
    private UserRole role = UserRole.USER;
}

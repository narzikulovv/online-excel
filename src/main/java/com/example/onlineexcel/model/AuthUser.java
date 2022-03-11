package com.example.onlineexcel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "auth",name = "auth_user")
public class AuthUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false, unique = true)
        private Long id;

        @Column(name = )
        private String fullName;

        private

}

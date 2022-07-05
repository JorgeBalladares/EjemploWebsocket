package com.example.EJ2.Persona.Domain.Entities;

import com.example.EJ2.Role.domain.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "persona") @Data @AllArgsConstructor @NoArgsConstructor

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "valor usuario obligatorio")
    @Column (name = "usuario", length = 10, unique = true)
    private String usuario;

    @NotBlank (message = "valor password obligatorio")
    @Column (name = "password")
    private String password;

    @Column (name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "company_email", nullable = false)
    private String company_email;

    @Column(name = "personal_email", nullable = false)
    private String personal_email;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_date", nullable = false)
    private Date created_date;

    @Column(name = "imagen_url")
    private String imagen_url;

    @Column(name = "termination_date")
    private Date termination_date;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}

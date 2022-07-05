package com.example.EJ2.Role.infraestructure.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoleInput {

    private int id;
    private String name;


    public RoleInput(Integer integer, String role_user) {
    }
}

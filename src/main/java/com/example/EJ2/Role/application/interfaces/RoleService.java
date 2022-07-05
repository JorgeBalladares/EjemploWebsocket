package com.example.EJ2.Role.application.interfaces;

import com.example.EJ2.Role.domain.Role;
import com.example.EJ2.Role.infraestructure.dtos.RoleInput;
import com.example.EJ2.Role.infraestructure.dtos.RoleOutput;
import org.springframework.context.annotation.Configuration;

import java.util.List;


public interface RoleService {

    public RoleOutput saveRole (RoleInput role) throws Exception;

    public void addRoltoUser (String usuario, String name) throws Exception;

    public List<Role> lista ();
}


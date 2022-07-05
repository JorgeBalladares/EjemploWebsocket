package com.example.EJ2.Role.infraestructure.controller;

import com.example.EJ2.Role.application.usercase.RoleServiceImpl;
import com.example.EJ2.Role.domain.Role;
import com.example.EJ2.Role.infraestructure.dtos.RoleInput;
import com.example.EJ2.Role.infraestructure.dtos.RoleOutput;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl serviceRol;

    @PostMapping("/addrole")
    public RoleOutput addRole (@RequestBody RoleInput inputRol) throws Exception {
        return serviceRol.saveRole(inputRol);
    }

    @PostMapping("/addroletouser")
    public void addRoleToUser (@RequestBody RoletoUserForm form) throws Exception {
        serviceRol.addRoltoUser(form.getUsuario(), form.getName());
    }

    @GetMapping ("/allrole")
    public List<Role> listaRoles(){
        return serviceRol.lista();
    }

}

@Data
class RoletoUserForm{
    private String usuario;
    private String name;
}

package com.example.EJ2.Role.application.usercase;

import com.example.EJ2.Persona.Domain.Entities.Persona;
import com.example.EJ2.Persona.Domain.repositories.PersonaRepository;
import com.example.EJ2.Role.application.interfaces.RoleService;
import com.example.EJ2.Role.domain.Role;
import com.example.EJ2.Role.infraestructure.dtos.RoleInput;
import com.example.EJ2.Role.infraestructure.dtos.RoleOutput;
import com.example.EJ2.Role.infraestructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repoRole;
    private final PersonaRepository repoPersona;
    private final ModelMapper model;


    public RoleOutput saveRole (RoleInput role) throws Exception {
        String rolename = role.getName();
        int id = role.getId();
        Role roleEntity = repoRole.getByName(rolename);
        if (roleEntity!=null){
            throw new Exception("rol ya creado");
        }
        else {
            repoRole.save(model.map(role, Role.class));
        }
        return model.map(role, RoleOutput.class);
    }

    public void addRoltoUser (String usuario, String name) throws Exception {
        Persona person = repoPersona.findByUsuario(usuario);
        Collection<Role> role = person.getRoles();
        Role rol = repoRole.getByName(name);
        if (person !=null){
            if (rol!=null){
                if (role.contains(rol)){
                    throw new Exception("Rol ya asignado a la persona");
                }
                else {
                    person.getRoles().add(rol);
                    repoPersona.save(person);
                }
            }
            else throw new Exception("El tipo de rol no ha sido creado previamente");
        }
        else throw new Exception("Usuario no existente");
    }

    public List<Role> lista (){
        return repoRole.findAll();
    }

}

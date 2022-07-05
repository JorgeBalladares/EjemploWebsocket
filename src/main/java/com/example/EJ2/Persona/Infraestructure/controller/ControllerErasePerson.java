package com.example.EJ2.Persona.Infraestructure.controller;


import com.example.EJ2.Exception.Customizer.NotFoundException;
import com.example.EJ2.Persona.Application.UserCases.PersonaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerErasePerson {

    @Autowired
    private PersonaServiceImpl servicio;
    @Autowired
    private ModelMapper model;

    @DeleteMapping("/asignacion/{id}")
    public ResponseEntity<String> eraseObj(@PathVariable("id") int id) throws Exception {
        try {
            servicio.deleteObj(id);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }catch (Exception e){
            //return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            throw new NotFoundException("valor no existe");
        }
    }
}

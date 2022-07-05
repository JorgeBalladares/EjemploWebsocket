package com.example.EJ2.Persona.Infraestructure.controller;


import com.example.EJ2.Persona.Application.UserCases.PersonaServiceImpl;
import com.example.EJ2.Persona.Infraestructure.dto.Inputs.PersonaInputDTO;
import com.example.EJ2.Persona.Infraestructure.dto.Outputs.PersonaOutSimpleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControlleAddPerson {
    @Autowired
    private PersonaServiceImpl servicio;
    @Autowired
    private ModelMapper model;

    @PostMapping(value = "/addPersona")
    //Al haber etiqueta Valid recogera el error 400 del HandlerControlAdvice y si se comenta esta clase genera error 500
    //ya que no sabe que error recoger y envía el genérico. Es algo raro de entender
    public ResponseEntity<PersonaOutSimpleDTO> addPerson(@RequestBody @Valid PersonaInputDTO p1Dto) throws Exception {
        //try {
        PersonaOutSimpleDTO p = servicio.addPersona(p1Dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(p);
        //}catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        //}
    }


}



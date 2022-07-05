package com.example.EJ2.Persona.Infraestructure.controller;


import com.example.EJ2.Persona.Application.UserCases.PersonaServiceImpl;
import com.example.EJ2.Persona.Infraestructure.dto.Outputs.PersonaOutSimpleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerGetListAllPerson {

    @Autowired
    private PersonaServiceImpl servicio;
    @Autowired
    private ModelMapper model;

    @GetMapping(value = "/allDisplay")
    public List<PersonaOutSimpleDTO> getAllData() throws Exception {
        if (servicio.getTotalList().isEmpty()){
            return null;
        }
        else {
            return servicio.getTotalList();
        }
    }
}

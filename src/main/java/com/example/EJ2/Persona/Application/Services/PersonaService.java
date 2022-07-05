package com.example.EJ2.Persona.Application.Services;


import com.example.EJ2.Persona.Domain.Entities.Persona;
import com.example.EJ2.Persona.Infraestructure.dto.Inputs.PersonaInputDTO;
import com.example.EJ2.Persona.Infraestructure.dto.Outputs.PersonaOutSimpleDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
        public PersonaOutSimpleDTO addPersona(PersonaInputDTO persona) throws Exception;

        public PersonaOutSimpleDTO getByID(int ID) throws Exception;

        public List<PersonaInputDTO> getPersonByName (String name);

        public PersonaOutSimpleDTO updPerson(int id, PersonaInputDTO personaInputDTO) throws Exception;

        public List<PersonaOutSimpleDTO> getTotalList() throws Exception;

        public void deleteObj(int id) throws Exception;



}

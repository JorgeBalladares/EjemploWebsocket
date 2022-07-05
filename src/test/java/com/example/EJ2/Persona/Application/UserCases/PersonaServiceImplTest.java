package com.example.EJ2.Persona.Application.UserCases;

import com.example.EJ2.Persona.Domain.repositories.PersonaRepository;
import com.example.EJ2.Persona.Infraestructure.dto.Inputs.PersonaInputDTO;
import com.example.EJ2.Persona.Infraestructure.dto.Outputs.PersonaOutSimpleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PersonaServiceImplTest {

    @Mock
    private PersonaInputDTO persona;
    @InjectMocks
    private final ModelMapper modelMapper = mock(ModelMapper.class);
    @Spy
    PersonaRepository repoMock = mock(PersonaRepository.class);
    @InjectMocks
    private PersonaServiceImpl service = new PersonaServiceImpl(repoMock, modelMapper);

    @Mock
    PersonaOutSimpleDTO p;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        persona = new PersonaInputDTO();
        persona.setName("Jorge");
        persona.setSurname("Ap");
        persona.setId(11);
        persona.setPersonal_email("jorge@jorge");
        persona.setCompany_email("jorge@jorge");
        persona.setPassword("1234");
        persona.setCity("jaen");
        persona.setUsuario("Jorge");
        //service = new PersonaServiceImpl(repoMock, modelMapper);
    }

    @Test
    void addPersona() throws Exception {

        when(service.addPersona(persona)).thenReturn(new PersonaOutSimpleDTO());
        p = service.addPersona(persona);
        Assertions.assertNotNull(p);
        //Assertions.assertNotNull(outPerson);
    }

    @Test
    void getByID() {
      /*  int id = persona.getId();
        Optional<Persona> personaOptional  = repoMock.findById(persona.getId());
        when(service.getByID(persona.getId())).thenReturn(personaOptional);
        Assertions.assertTrue(personaOptional.isPresent());*/
    }

    @Test
    void getPersonByName() {
    }

    @Test
    void updPerson() {
    }

    @Test
    void getTotalList() {
    }

    @Test
    void deleteObj() {
    }
}
package com.example.EJ2.Persona.Application.UserCases;


import com.example.EJ2.Persona.Application.Services.PersonaService;
import com.example.EJ2.Persona.Domain.Entities.Persona;
import com.example.EJ2.Persona.Domain.repositories.PersonaRepository;
import com.example.EJ2.Persona.Infraestructure.dto.Inputs.PersonaInputDTO;
import com.example.EJ2.Persona.Infraestructure.dto.Outputs.PersonaOutSimpleDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Component
@RequiredArgsConstructor
@Slf4j
public class PersonaServiceImpl implements PersonaService, UserDetailsService {
   // @Autowired
    private final PersonaRepository personaRepositorio;
    //@Autowired
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private List<Persona> listaPerson;


    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Persona persona = personaRepositorio.findByUsuario(usuario);
        if(persona ==null){
            throw new UsernameNotFoundException("Persona no encontrada");
        } else {
            System.out.println("Usuario encontrado");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        persona.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        PersonaOutSimpleDTO per = modelMapper.map(persona, PersonaOutSimpleDTO.class);
        per.setPassword(passwordEncoder.encode(persona.getPassword()));
        return new org.springframework.security.core.userdetails.User(per.getUsuario(), per.getPassword(), authorities);
    }


    public PersonaOutSimpleDTO addPersona(PersonaInputDTO persona) throws Exception {
        String username = persona.getUsuario();
        Persona personRepo = personaRepositorio.findByUsuario(username);
        if(personRepo!=null){
            throw new Exception("Usuario ya existente");
        }
        else{

            personaRepositorio.save(modelMapper.map(persona, Persona.class));
            PersonaOutSimpleDTO per = modelMapper.map(persona, PersonaOutSimpleDTO.class);
            //Codificamos la salida del password
            per.setPassword(passwordEncoder.encode(per.getPassword()));
            return per;
        }

    }

    @Override
    public PersonaOutSimpleDTO getByID(int id) throws Exception {
        Optional<Persona> person = personaRepositorio.findById(id);
        PersonaOutSimpleDTO p = modelMapper.map(person, PersonaOutSimpleDTO.class);
        if (person.isPresent()) {
            p.setPassword(passwordEncoder.encode(p.getPassword()));
            return p;
        }
        return null;
    }


    public List<PersonaInputDTO> getPersonByName(String name) {
        listaPerson = personaRepositorio.findByName(name);
        return listaPerson.stream()
                .map(Persona -> modelMapper.map(Persona, PersonaInputDTO.class))
                .collect(Collectors.toList());
    }

    public PersonaOutSimpleDTO updPerson(int id, PersonaInputDTO personaInputDTO) throws Exception {
        Optional<Persona> persona = personaRepositorio.findById(id);
        Persona persona1 = modelMapper.map(persona, Persona.class);
        Persona person = modelMapper.map(personaInputDTO, Persona.class);
        if(!persona.isPresent()){
            throw new Exception("No existe una persona con el id buscado");
        }
        else {
            if(person.getId()==persona1.getId()) {
                personaRepositorio.save(person);
                return modelMapper.map(person, PersonaOutSimpleDTO.class);
            }
            else {
                throw new Exception("id no asignado, no se puede actualizar");
            }
        }
    }


    public List<PersonaOutSimpleDTO> getTotalList() throws Exception {
        List<Persona> lista = personaRepositorio.findAll();

        return lista.stream()
                .map(Persona -> modelMapper.map(Persona, PersonaOutSimpleDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteObj(int id) throws Exception {
        personaRepositorio.deleteById(id);
    }



}

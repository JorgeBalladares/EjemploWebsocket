package com.example.EJ2.Persona.Infraestructure.controller;

import com.example.EJ2.Exception.Customizer.NotFoundException;
import com.example.EJ2.Exception.Customizer.UnprocesableException;
import com.example.EJ2.Persona.Application.UserCases.PersonaServiceImpl;
import com.example.EJ2.Persona.Domain.Entities.Persona;
import com.example.EJ2.Persona.Domain.repositories.PersonaRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Logged {

    @Autowired
    PersonaServiceImpl personService;
    @Autowired
    PersonaRepository repoPerson;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping ("/login")
    public ResponseEntity<UserDetails> login (@RequestParam ("usuario") String username, @RequestParam ("password") String pwd) throws Exception {

       Optional<Persona> persoFindUser = Optional.ofNullable(repoPerson.findByUsuario(username));
       Persona persona = modelMapper.map(persoFindUser, Persona.class);

        if(persoFindUser.isEmpty()) throw new NotFoundException("Usuario " + username+" no encontrado");
        else {
            String password = persona.getPassword();
            if (!pwd.equals(password)) throw  new UnprocesableException("Contraseña erronea");
            else {
                return new ResponseEntity<>(personService.loadUserByUsername(username), HttpStatus.OK);
            }
        }

    }


    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(username);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }



}

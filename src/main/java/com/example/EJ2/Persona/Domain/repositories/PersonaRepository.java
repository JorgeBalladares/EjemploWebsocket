package com.example.EJ2.Persona.Domain.repositories;


import com.example.EJ2.Persona.Domain.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    List<Persona> findByName(String name);
    Persona findByUsuario(String usuario);
}

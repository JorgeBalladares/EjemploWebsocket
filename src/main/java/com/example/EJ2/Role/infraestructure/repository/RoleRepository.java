package com.example.EJ2.Role.infraestructure.repository;

import com.example.EJ2.Role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role getByName (String name);
}

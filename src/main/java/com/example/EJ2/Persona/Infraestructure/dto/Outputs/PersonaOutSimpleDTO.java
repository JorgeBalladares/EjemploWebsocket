package com.example.EJ2.Persona.Infraestructure.dto.Outputs;

import com.example.EJ2.Role.domain.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
public class PersonaOutSimpleDTO {

    private int id;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private Collection<Role> roles = new ArrayList<>();


}

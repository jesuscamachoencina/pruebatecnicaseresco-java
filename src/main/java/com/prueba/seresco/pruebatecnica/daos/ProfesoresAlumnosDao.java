package com.prueba.seresco.pruebatecnica.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

@Repository
public interface ProfesoresAlumnosDao extends CrudRepository<ProfesoresAlumnos, Long> {

}

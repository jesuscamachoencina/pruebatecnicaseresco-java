package com.prueba.seresco.pruebatecnica.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prueba.seresco.pruebatecnica.entities.Profesor;

@Repository
public interface ProfesorDao extends CrudRepository<Profesor, Long> {

}

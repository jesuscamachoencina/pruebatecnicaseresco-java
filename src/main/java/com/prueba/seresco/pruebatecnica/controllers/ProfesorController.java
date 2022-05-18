package com.prueba.seresco.pruebatecnica.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.seresco.pruebatecnica.daos.ProfesorDao;
import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.Profesor;
import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

	@Autowired
	private ProfesorDao profesorDao;

	@Autowired
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@GetMapping("/all")
	public ResponseEntity<List<Profesor>> getAllProfesores() {
		try {
			List<Profesor> profesor = (List<Profesor>) profesorDao.findAll();

			if (CollectionUtils.isEmpty(profesor)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(profesor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Long> saveProfesor(@RequestBody Profesor nuevoProfesor) {
		try {
			Profesor profesor = profesorDao.save(nuevoProfesor);
			return new ResponseEntity<>(profesor.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Long> updateProfesor(@RequestBody Profesor profesor) {
		try {
			Profesor profesorActualizado = profesorDao.save(profesor);
			return new ResponseEntity<>(profesorActualizado.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProfesor(@PathVariable Long id) {
		try {
			List<ProfesoresAlumnos> relacion = (List<ProfesoresAlumnos>) profesoresAlumnosDao.findAll();

			List<Long> ids = relacion.stream().filter(rel -> id.equals(rel.getProfesor().getId()))
					.map(ProfesoresAlumnos::getId).collect(Collectors.toList());

			profesoresAlumnosDao.deleteAllById(ids);
			profesorDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

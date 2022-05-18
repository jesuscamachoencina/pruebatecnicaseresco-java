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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

@RestController
@RequestMapping("/profesoresAlumnos")
public class ProfesoresAlumnosController {

	@Autowired
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@GetMapping("/{id}")
	public ResponseEntity<List<ProfesoresAlumnos>> getAlumnosPorProfesor(@PathVariable Long id) {
		try {
			List<ProfesoresAlumnos> profesoresAlumnos = (List<ProfesoresAlumnos>) profesoresAlumnosDao.findAll();
			profesoresAlumnos = profesoresAlumnos.stream().filter(pa -> pa.getProfesor().getId().equals(id))
					.collect(Collectors.toList());

			if (CollectionUtils.isEmpty(profesoresAlumnos)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(profesoresAlumnos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Long> saveProfesorAlumno(@RequestBody ProfesoresAlumnos nuevaRelacion) {
		try {
			ProfesoresAlumnos relacion = profesoresAlumnosDao.save(nuevaRelacion);
			return new ResponseEntity<>(relacion.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProfesorAlumno(@PathVariable Long id) {
		try {
			profesoresAlumnosDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

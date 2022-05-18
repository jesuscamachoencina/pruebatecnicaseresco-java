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

import com.prueba.seresco.pruebatecnica.daos.AlumnoDao;
import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.Alumno;
import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoDao alumnoDao;

	@Autowired
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@GetMapping("/all")
	public ResponseEntity<List<Alumno>> getAllAlumnos() {
		try {
			List<Alumno> alumnos = (List<Alumno>) alumnoDao.findAll();

			if (CollectionUtils.isEmpty(alumnos)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alumnos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/idProfesor/{id}")
	public ResponseEntity<List<Alumno>> getAlumnosPorProfesor(@PathVariable Long id) {
		try {
			List<ProfesoresAlumnos> relacion = (List<ProfesoresAlumnos>) profesoresAlumnosDao.findAll();

			List<Long> idsAlumno = relacion.stream().filter(rel -> id.equals(rel.getProfesor().getId()))
					.map(ProfesoresAlumnos::getAlumno).map(Alumno::getId).collect(Collectors.toList());

			List<Alumno> alumnos = (List<Alumno>) alumnoDao.findAllById(idsAlumno);

			if (CollectionUtils.isEmpty(alumnos)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(alumnos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Long> saveAlumno(@RequestBody Alumno nuevoAlumno) {
		try {
			Alumno alumno = alumnoDao.save(nuevoAlumno);
			return new ResponseEntity<>(alumno.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Long> updateAlumno(@RequestBody Alumno alumno) {
		try {
			Alumno alumnoActualizado = alumnoDao.save(alumno);
			return new ResponseEntity<>(alumnoActualizado.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteAlumno(@PathVariable Long id) {
		try {
			List<ProfesoresAlumnos> relacion = (List<ProfesoresAlumnos>) profesoresAlumnosDao.findAll();

			List<Long> ids = relacion.stream().filter(rel -> id.equals(rel.getAlumno().getId()))
					.map(ProfesoresAlumnos::getId).collect(Collectors.toList());

			profesoresAlumnosDao.deleteAllById(ids);
			alumnoDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

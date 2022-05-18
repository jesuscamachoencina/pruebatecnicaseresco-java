package com.prueba.seresco.pruebatecnica.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.Profesor;
import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

@ExtendWith(MockitoExtension.class)
class ProfesoresAlumnosControllerTest {

	@InjectMocks
	private ProfesoresAlumnosController profesoresAlumnosController;

	@Mock
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@Test
	void testGetAlumnosPorProfesor() {
		ProfesoresAlumnos profesorAlumno = new ProfesoresAlumnos();
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		profesorAlumno.setProfesor(profesor);
		List<ProfesoresAlumnos> profesoresAlumnos = Lists.list(profesorAlumno);

		when(profesoresAlumnosDao.findAll()).thenReturn(profesoresAlumnos);

		ResponseEntity<List<ProfesoresAlumnos>> response = profesoresAlumnosController.getAlumnosPorProfesor(1L);
		assertEquals(profesoresAlumnos, response.getBody());
	}
	
	@Test
	void testGetAlumnosPorProfesorEmptyList() {
		when(profesoresAlumnosDao.findAll()).thenReturn(Lists.newArrayList());

		ResponseEntity<List<ProfesoresAlumnos>> response = profesoresAlumnosController.getAlumnosPorProfesor(1L);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testSaveProfesor() {
		ProfesoresAlumnos profesorAlumno = new ProfesoresAlumnos();
		profesorAlumno.setId(1L);

		when(profesoresAlumnosDao.save(any(ProfesoresAlumnos.class))).thenReturn(profesorAlumno);

		ResponseEntity<Long> response = profesoresAlumnosController.saveProfesorAlumno(new ProfesoresAlumnos());
		assertEquals(profesorAlumno.getId(), response.getBody());
	}

	@Test
	void testDeleteProfesor() {
		ResponseEntity<HttpStatus> response = profesoresAlumnosController.deleteProfesorAlumno(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}

package com.prueba.seresco.pruebatecnica.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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

import com.prueba.seresco.pruebatecnica.daos.AlumnoDao;
import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.Alumno;

@ExtendWith(MockitoExtension.class)
class AlumnoControllerTest {

	@InjectMocks
	private AlumnoController alumnoController;

	@Mock
	private AlumnoDao alumnoDao;
	@Mock
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@Test
	void testGetAllAlumnos() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);
		List<Alumno> alumnos = Lists.list(alumno);

		when(alumnoDao.findAll()).thenReturn(alumnos);

		ResponseEntity<List<Alumno>> response = alumnoController.getAllAlumnos();
		assertEquals(alumnos, response.getBody());
	}

	@Test
	void testGetAllAlumnosEmptyList() {
		when(alumnoDao.findAll()).thenReturn(Lists.newArrayList());

		ResponseEntity<List<Alumno>> response = alumnoController.getAllAlumnos();
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testGetAlumnosPorProfesor() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);
		List<Alumno> alumnos = Lists.list(alumno);

		when(profesoresAlumnosDao.findAll()).thenReturn(Lists.newArrayList());
		when(alumnoDao.findAllById(anyList())).thenReturn(alumnos);

		ResponseEntity<List<Alumno>> response = alumnoController.getAlumnosPorProfesor(1L);
		assertEquals(alumnos, response.getBody());
	}

	@Test
	void testGetAlumnosPorProfesorEmptyList() {
		when(profesoresAlumnosDao.findAll()).thenReturn(Lists.newArrayList());
		when(alumnoDao.findAllById(anyList())).thenReturn(Lists.newArrayList());

		ResponseEntity<List<Alumno>> response = alumnoController.getAlumnosPorProfesor(1L);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testSaveAlumno() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);

		when(alumnoDao.save(any(Alumno.class))).thenReturn(alumno);

		ResponseEntity<Long> response = alumnoController.saveAlumno(new Alumno());
		assertEquals(alumno.getId(), response.getBody());
	}

	@Test
	void testUpdateAlumno() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);

		when(alumnoDao.save(any(Alumno.class))).thenReturn(alumno);

		ResponseEntity<Long> response = alumnoController.updateAlumno(new Alumno());
		assertEquals(alumno.getId(), response.getBody());
	}

	@Test
	void testDeleteAlumno() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);

		when(profesoresAlumnosDao.findAll()).thenReturn(Lists.newArrayList());

		ResponseEntity<HttpStatus> response = alumnoController.deleteAlumno(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}

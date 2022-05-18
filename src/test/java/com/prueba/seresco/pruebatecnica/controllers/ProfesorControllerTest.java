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

import com.prueba.seresco.pruebatecnica.daos.ProfesorDao;
import com.prueba.seresco.pruebatecnica.daos.ProfesoresAlumnosDao;
import com.prueba.seresco.pruebatecnica.entities.Profesor;

@ExtendWith(MockitoExtension.class)
class ProfesorControllerTest {

	@InjectMocks
	private ProfesorController profesorController;

	@Mock
	private ProfesorDao profesorDao;
	@Mock
	private ProfesoresAlumnosDao profesoresAlumnosDao;

	@Test
	void testGetAllProfesores() {
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		List<Profesor> profesores = Lists.list(profesor);

		when(profesorDao.findAll()).thenReturn(profesores);

		ResponseEntity<List<Profesor>> response = profesorController.getAllProfesores();
		assertEquals(profesores, response.getBody());
	}

	@Test
	void testGetAllProfesoresEmptyList() {
		when(profesorDao.findAll()).thenReturn(Lists.newArrayList());
		
		ResponseEntity<List<Profesor>> response = profesorController.getAllProfesores();
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void testSaveProfesor() {
		Profesor profesor = new Profesor();
		profesor.setId(1L);

		when(profesorDao.save(any(Profesor.class))).thenReturn(profesor);

		ResponseEntity<Long> response = profesorController.saveProfesor(new Profesor());
		assertEquals(profesor.getId(), response.getBody());
	}

	@Test
	void testUpdateProfesor() {
		Profesor profesor = new Profesor();
		profesor.setId(1L);

		when(profesorDao.save(any(Profesor.class))).thenReturn(profesor);

		ResponseEntity<Long> response = profesorController.updateProfesor(new Profesor());
		assertEquals(profesor.getId(), response.getBody());
	}

	@Test
	void testDeleteProfesor() {
		Profesor profesor = new Profesor();
		profesor.setId(1L);

		when(profesoresAlumnosDao.findAll()).thenReturn(Lists.newArrayList());

		ResponseEntity<HttpStatus> response = profesorController.deleteProfesor(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}

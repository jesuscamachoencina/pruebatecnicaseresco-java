package com.prueba.seresco.pruebatecnica.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.prueba.seresco.pruebatecnica.entities.Alumno;

class AlumnoTest {

	private Alumno alumno = new Alumno();

	@Test
	void testAlumno() {
		alumno = new Alumno("Juan", "Pérez Pérez", "3º ESO");
		assertEquals("Juan", alumno.getNombre());
		assertEquals("Pérez Pérez", alumno.getApellido());
		assertEquals("3º ESO", alumno.getCurso());
	}

	@Test
	void testGetId() {
		alumno.setId(1L);
		assertEquals(1L, alumno.getId());
	}

	@Test
	void testGetNombre() {
		alumno.setNombre("Juan");
		assertEquals("Juan", alumno.getNombre());
	}

	@Test
	void testGetApellido() {
		alumno.setApellido("Pérez Duarte");
		assertEquals("Pérez Duarte", alumno.getApellido());
	}

	@Test
	void testGetCurso() {
		alumno.setCurso("3º Primaria");
		assertEquals("3º Primaria", alumno.getCurso());
	}

}

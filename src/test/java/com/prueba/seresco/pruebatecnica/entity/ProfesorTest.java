package com.prueba.seresco.pruebatecnica.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.prueba.seresco.pruebatecnica.entities.Profesor;

class ProfesorTest {

	private Profesor profesor = new Profesor();

	@Test
	void testProfesor() {
		profesor = new Profesor("Juan", "Pérez Pérez", "Matemáticas");
		assertEquals("Juan", profesor.getNombre());
		assertEquals("Pérez Pérez", profesor.getApellido());
		assertEquals("Matemáticas", profesor.getMateria());
	}

	@Test
	void testGetId() {
		profesor.setId(1L);
		assertEquals(1L, profesor.getId());
	}

	@Test
	void testGetNombre() {
		profesor.setNombre("Juan");
		assertEquals("Juan", profesor.getNombre());
	}

	@Test
	void testGetApellido() {
		profesor.setApellido("Pérez Duarte");
		assertEquals("Pérez Duarte", profesor.getApellido());
	}

	@Test
	void testGetMateria() {
		profesor.setMateria("Geografía");
		assertEquals("Geografía", profesor.getMateria());
	}

}

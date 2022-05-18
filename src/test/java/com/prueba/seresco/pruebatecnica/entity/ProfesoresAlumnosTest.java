package com.prueba.seresco.pruebatecnica.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.prueba.seresco.pruebatecnica.entities.Alumno;
import com.prueba.seresco.pruebatecnica.entities.Profesor;
import com.prueba.seresco.pruebatecnica.entities.ProfesoresAlumnos;

class ProfesoresAlumnosTest {

	private ProfesoresAlumnos profesoresAlumnos = new ProfesoresAlumnos();

	@Test
	void testGetId() {
		profesoresAlumnos.setId(1L);
		assertEquals(1L, profesoresAlumnos.getId());
	}

	@Test
	void testGetProfesor() {
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		profesoresAlumnos.setProfesor(profesor);
		assertEquals(1L, profesoresAlumnos.getProfesor().getId());
	}

	@Test
	void testGetAlumno() {
		Alumno alumno = new Alumno();
		alumno.setId(1L);
		profesoresAlumnos.setAlumno(alumno);
		assertEquals(1L, profesoresAlumnos.getAlumno().getId());
	}

}

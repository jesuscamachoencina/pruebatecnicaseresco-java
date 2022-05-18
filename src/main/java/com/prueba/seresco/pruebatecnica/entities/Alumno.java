package com.prueba.seresco.pruebatecnica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ALUMNOS")
@NoArgsConstructor
@Getter
@Setter
public class Alumno {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "APELLIDO", nullable = false)
	private String apellido;

	@Column(name = "CURSO", nullable = false)
	private String curso;

	public Alumno(String nombre, String apellido, String curso) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.curso = curso;
	}
}

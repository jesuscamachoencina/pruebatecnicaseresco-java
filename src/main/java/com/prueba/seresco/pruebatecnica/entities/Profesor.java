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
@Table(name = "PROFESORES")
@NoArgsConstructor
@Getter
@Setter
public class Profesor {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "APELLIDO", nullable = false)
	private String apellido;

	@Column(name = "MATERIA", nullable = false)
	private String materia;

	public Profesor(String nombre, String apellido, String materia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.materia = materia;
	}
}

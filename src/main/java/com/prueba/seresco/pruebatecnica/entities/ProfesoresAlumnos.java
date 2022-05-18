package com.prueba.seresco.pruebatecnica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROFESORES_ALUMNOS")
@NoArgsConstructor
@Getter
@Setter
public class ProfesoresAlumnos {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;

	@ManyToOne
	@JoinColumn(name = "ID_ALUMNO")
	private Alumno alumno;
}

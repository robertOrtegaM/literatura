package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name ="autores")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer deceso;
}

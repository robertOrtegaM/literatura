package com.alura.literatura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String titulos;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private List<Authors> authors;
    @Enumerated(EnumType.STRING)
    private Summaries descripcion;
    @Enumerated(EnumType.STRING)
    private Languages languages;

    public List<Authors> getAuthors() {
        return authors;
    }
    public Libros() {
    }
    public Libros(DatosLibros datosLibros) {
        this.id = datosLibros.id();
        this.titulos =datosLibros.titulo();
        this.descripcion = Summaries.fromString(datosLibros.description().split(",")[0].trim());
        this.languages = Languages.fromString(datosLibros.languages().split(",")[0].trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}

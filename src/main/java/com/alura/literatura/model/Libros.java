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
    private String titulo;
    @OneToMany(mappedBy = "actores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> autores;
    private String summaries;
    @Enumerated(EnumType.STRING)
    private Languages languages;

    public List<Authors> getAutores() {
        return autores;
    }

    public Libros(DatosLibros datosLibros) {
        this.id = datosLibros.id();
        this.titulo =datosLibros.titulo();
        this.summaries = datosLibros.summaries();
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

    public String getSummaries() {
        return summaries;
    }

    public void setSummaries(String summaries) {
        this.summaries = summaries;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}

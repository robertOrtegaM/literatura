package com.alura.literatura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("id") int id,
        @JsonAlias("title") String titulo,
        @JsonAlias("summaries") String description,
        @JsonAlias("languages") String languages) {
}

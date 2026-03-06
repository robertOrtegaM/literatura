package com.alura.literatura.repository;

import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Languages;
import com.alura.literatura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros, Integer> {

    // 1. Buscar libros por idioma
    List<Libros> findByLanguages(Languages languages);

    // 2. Top 10 libros más descargados
    // ⚠️ SOLO si existe el campo descargas
    // List<Libros> findTop10ByOrderByDescargasDesc();

    // 3. Listar autores registrados
    @Query("SELECT DISTINCT a FROM Libros l JOIN l.authors a")
    List<Authors> listarAutores();

    // 4. Buscar autor por nombre
    @Query("SELECT DISTINCT a FROM Libros l JOIN l.authors a WHERE a.nombre LIKE %:nombre%")
    List<Authors> buscarAutorPorNombre(String nombre);

    // 5. Autores vivos en un año
    @Query("""
        SELECT DISTINCT a FROM Libros l JOIN l.authors a
        WHERE a.nacimiento <= :anio
        AND (a.deceso IS NULL OR a.deceso >= :anio)
    """)
    List<Authors> listarAutoresVivos(int anio);
}
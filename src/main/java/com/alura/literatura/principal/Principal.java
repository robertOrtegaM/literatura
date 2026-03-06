package com.alura.literatura.principal;

import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosLibros;
import com.alura.literatura.model.Languages;
import com.alura.literatura.model.Libros;
import com.alura.literatura.repository.LibrosRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSCAR = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;

    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------------
                    1. Buscar libro por título (Web)
                    2. Listar libros registrados
                    3. Buscar autor por nombre
                    4. Listar autores registrados
                    5. Listar autores vivos en un determinado año
                    6. Listar libros por idioma
                    7. Top 10 libros más descargados
                    0. Salir
                    ----------------------------------
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1 -> buscarLibroWeb();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> buscarAutorPorNombre();
                    case 4 -> listarAutoresRegistrados();
                    case 5 -> listarAutoresVivosPorAnio();
                    case 6 -> listarLibrosPorIdioma();
                    case 7 -> top10Libros();
                    case 0 -> System.out.println("Cerrando la aplicación...");
                    default -> System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número.");
                teclado.nextLine();
            }
        }
    }

    // --- LÓGICA DE LOS MÉTODOS ---

    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + BUSCAR + nombreLibro.replace(" ", "%20"));

        // Gutendex devuelve un objeto con una lista "results"
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado: " + libroBuscado.get());
            Libros libro = new Libros(libroBuscado.get());
            repositorio.save(libro); // Guardamos en la DB
            System.out.println("Libro guardado con éxito.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libros> libros = repositorio.findAll();
        libros.forEach(System.out::println);
    }

    private void buscarAutorPorNombre() {
        System.out.println("Escribe el nombre del autor:");
        var nombre = teclado.nextLine();
        // Aquí deberías tener un método en el repository: findByAutorNombreContainingIgnoreCase
        var autores = repositorio.buscarAutorPorNombre(nombre);
        autores.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        // Asumiendo que tienes una entidad Autor o que extraes los autores de Libros
        repositorio.listarAutores().forEach(System.out::println);
    }

    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingresa el año para verificar autores vivos:");
        var anio = teclado.nextInt();
        teclado.nextLine();
        repositorio.listarAutoresVivos(anio).forEach(System.out::println);
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma:
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """);
        var idioms = teclado.nextLine();
        repositorio.findByLanguages(Languages.valueOf(idioms)).forEach(System.out::println);
    }

    private void top10Libros() {
        repositorio.listarAutores().forEach(System.out::println);
    }
}
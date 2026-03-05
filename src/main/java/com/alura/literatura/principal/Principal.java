package com.alura.literatura.principal;

import com.alura.literatura.model.DatosLibros;
import com.alura.literatura.model.Libros;
import com.alura.literatura.repository.LibrosRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSCAR = "?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros = new ArrayList<>();
    private LibrosRepository repositorio;
    private List<Libros> Libros;
    private Optional<Libros> libroBuscado;

    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1. Buscar libro por título
                    2. Listar libros registrados
                    3. Buscar autor por nombre
                    4. Listar autores registrados
                    5. Listar autores vivos en un determinado año
                    6. Listar libros por idioma
                    7. Top 10 libros más descargados
                    0. Salir
                    
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
//                    registroDeLibros();
//                    break;
//                case 3:
//                    mostrarLibrosBuscados();
//                    break;
//                case 4:
//                    buscarSeriesPorTitulo();
//                    break;
//                case 5:
//                    buscarTop5Series();
//                    break;
//                case 6:
//                    buscarSeriesPorCategoria();
//                    break;
//                case 7:
//                    filtrarSeriesPorTemporadaYEvaluacion();
//                    break;
//                case 8:
//                    buscarEpisodiosPorTitulo();
//                    break;
//                case 9:
//                    buscarTop5Episodios();
//                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibros getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE +BUSCAR+ nombreLibro.replace(" ", "%20"));
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;
    }
    private void registroDeLibros() {
        mostrarLibrosBuscados();
        System.out.println("Escribe el nombre del libro que quieres ver");
        var nombreSerie = teclado.nextLine();

        Optional<Libros> libro = Libros.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if(libro.isPresent()){
            var libroEncontrado = libro.get();
            List<DatosTemporadas> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obtenerDatos(URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DatosTemporadas datosTemporada = conversor.obtenerDatos(json, DatosTemporadas.class);
                temporadas.add(datosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }



    }
    private void buscarLibroWeb() {
        DatosLibros datos = getDatosLibro();
        Libros libro = new Libros(datos);
        repositorio.save(libro);
        //datosLibros.add(datos);
        System.out.println(datos);
    }

//    private void mostrarLibrosBuscados() {
//        series = repositorio.findAll();
//
//        series.stream()
//                .sorted(Comparator.comparing(Serie::getGenero))
//                .forEach(System.out::println);
//    }

//    private void buscarSeriesPorTitulo(){
//        System.out.println("Escribe el nombre de la serie que deseas buscar");
//        var nombreSerie = teclado.nextLine();
//        libroBuscado = repositorio.findByTituloContainsIgnoreCase(nombreSerie);
//
//        if(libroBuscado.isPresent()){
//            System.out.println("La serie buscada es: " + libroBuscado.get());
//        } else {
//            System.out.println("Serie no encontrada");
//        }
//
//    }
//    private void buscarTop5Series(){
//        List<Serie> topSeries = repositorio.findTop5ByOrderByEvaluacionDesc();
//        topSeries.forEach(s ->
//                System.out.println("Serie: " + s.getTitulo() + " Evaluacion: " + s.getEvaluacion()) );
//    }

//    private void buscarSeriesPorCategoria(){
//        System.out.println("Escriba el genero/categoría de la serie que desea buscar");
//        var genero = teclado.nextLine();
//        var categoria = Categoria.fromEspanol(genero);
//        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
//        System.out.println("Las series de la categoría " + genero);
//        seriesPorCategoria.forEach(System.out::println);
//    }
//    public void filtrarSeriesPorTemporadaYEvaluacion(){
//        System.out.println("¿Filtrar séries con cuántas temporadas? ");
//        var totalTemporadas = teclado.nextInt();
//        teclado.nextLine();
//        System.out.println("¿Com evaluación apartir de cuál valor? ");
//        var evaluacion = teclado.nextDouble();
//        teclado.nextLine();
//        List<Serie> filtroSeries = repositorio.seriesPorTemparadaYEvaluacion(totalTemporadas,evaluacion);
//        System.out.println("*** Series filtradas ***");
//        filtroSeries.forEach(s ->
//                System.out.println(s.getTitulo() + "  - evaluacion: " + s.getEvaluacion()));
//    }

//    private void  buscarEpisodiosPorTitulo(){
//        System.out.println("Escribe el nombre del episodio que deseas buscar");
//        var nombreEpisodio = teclado.nextLine();
//        List<Episodio> episodiosEncontrados = repositorio.episodiosPorNombre(nombreEpisodio);
//        episodiosEncontrados.forEach(e ->
//                System.out.printf("Serie: %s Temporada %s Episodio %s Evaluación %s\n",
//                        e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getEvaluacion()));
//
//    }

//    private void buscarTop5Episodios(){
//        buscarSeriesPorTitulo();
//        if(libroBuscado.isPresent()){
//            Serie serie = libroBuscado.get();
//            List<Episodio> topEpisodios = repositorio.top5Episodios(serie);
//            topEpisodios.forEach(e ->
//                    System.out.printf("Serie: %s - Temporada %s - Episodio %s - Evaluación %s\n",
//                            e.getSerie().getTitulo(), e.getTemporada(), e.getTitulo(), e.getEvaluacion()));
//
////        }
//    }
}

package com.alura.literatura.service;

import com.alura.literatura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LibrosService {
    @Autowired
    private LibrosRepository repository;

//    public List<SerieDTO> optenerSeries(){
//        return convierteDatos(repository.findAll());
//    }
//
//    public List<SerieDTO> optenerTop5() {
//        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
//
//    }
//    public List<SerieDTO> lanzamientos(){
//        return convierteDatos(repository.seriesEstrenos());
//    }
//
//    public List<SerieDTO> convierteDatos(List<Serie> series) {
//        return series.stream()
//                .map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getEvaluacion(),s.getPoster(),s.getGenero(),s.getActores(),s.getSinopsis()))
//                .collect(Collectors.toList());
//    }
//
//    public SerieDTO busquedaPorId(Long id) {
//        Optional<Serie> serie = repository.findById(id);
//        if(serie.isPresent()){
//            Serie s = serie.get();
//            return new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getEvaluacion(),s.getPoster(),s.getGenero(),s.getActores(),s.getSinopsis());
//        }
//        return null;
//    }
//
//    public List<EpisodioDTO> optenerTemporadas(Long id) {
//        Optional<Serie> serie = repository.findById(id);
//        if(serie.isPresent()){
//            Serie s = serie.get();
//            return s.getEpisodios().stream()
//                    .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
//                    .collect(Collectors.toList());
//        }
//        return  null;
//    }
//
//    public List<EpisodioDTO> optenerTemporadaPorId(Long id, Long id2) {
//        return repository.episodiosPorId(id, id2).stream()
//                .map(e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
//                .collect(Collectors.toList());
//    }
//
//    public List<SerieDTO> busquedaDecategoria(String nombreG) {
//        Categoria categoria = Categoria.fromEspanol(nombreG);
//        return convierteDatos(repository.findByGenero(categoria));
//
//    }

}

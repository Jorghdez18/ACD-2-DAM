package ies.puesto.peliculas.service.mapper;

import ies.puesto.peliculas.model.Pelicula;
import ies.puesto.peliculas.service.modelo.CreacionPelicula;
import ies.puesto.peliculas.service.modelo.EdicionPelicula;
import org.springframework.stereotype.Component;

/**
 * Clase PeliculaDominioMapper
 */
@Component
public class PeliculaDominioMapper {

    public Pelicula aPelicula(CreacionPelicula datos) {
        return new Pelicula(
                datos.referenciaSolicitada(), datos.nombreSolicitado(),
                datos.anioSolicitado(), datos.minutosSolicitados());
    }
    public Pelicula aPelicula(String identificador, EdicionPelicula datos) {
        return new Pelicula(
                identificador, datos.nombreSolicitado(),
                datos.anioSolicitado(), datos.minutosSolicitados());
    }
}

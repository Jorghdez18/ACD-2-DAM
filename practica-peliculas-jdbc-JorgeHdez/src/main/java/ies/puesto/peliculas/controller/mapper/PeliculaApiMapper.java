package ies.puesto.peliculas.controller.mapper;

import ies.puesto.peliculas.controller.api.PeliculaEdicionRequest;
import ies.puesto.peliculas.controller.api.PeliculaRequest;
import ies.puesto.peliculas.controller.api.PeliculaResponse;
import ies.puesto.peliculas.model.Pelicula;
import ies.puesto.peliculas.service.modelo.CreacionPelicula;
import ies.puesto.peliculas.service.modelo.EdicionPelicula;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase PeliculaApiMapper
 *
 */
@Component
public class PeliculaApiMapper {

    public CreacionPelicula aDatosCreacion(PeliculaRequest peticion) {
        return new CreacionPelicula(
                peticion.codigo(), peticion.titulo(),
                peticion.anioEstreno(), peticion.duracionMinutos());
    }

    public EdicionPelicula aDatosEdicion(PeliculaEdicionRequest peticion) {
        return new EdicionPelicula(
                peticion.titulo(), peticion.anioEstreno(), peticion.duracionMinutos());
    }

    public PeliculaResponse aRespuesta(Pelicula pelicula) {
        return new PeliculaResponse(
                pelicula.getIdentificador(), pelicula.getNombre(),
                pelicula.getAnioLanzamiento(), pelicula.getMinutosProyeccion());
    }

    public List<PeliculaResponse> aRespuestas(List<Pelicula> peliculaList) {
        List<PeliculaResponse> respuestas = new ArrayList<>();
        for (Pelicula pelicula : peliculaList) {
            respuestas.add(aRespuesta(pelicula));
        }
        return respuestas;
    }
}

package ies.puesto.peliculas.repository.jdbc;

import org.springframework.stereotype.Component;

/**
 *Clase PeliculaEntityMapper
 */
@Component
class PeliculaEntityMapper {
    PeliculaEntity aEntity(Pelicula pelicula) {
        return new PeliculaEntity(
                pelicula.getIdentificador(), pelicula.getNombre(),
                pelicula.getAnioLanzamiento(), pelicula.getMinutosProyeccion());
    }

    Pelicula aPelicula(PeliculaEntity entity) {
        return new Pelicula(
                entity.getCodigoFila(), entity.getTituloFila(),
                entity.getAnioFila(), entity.getMinutosFila());
    }


}

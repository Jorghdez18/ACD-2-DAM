package ies.puesto.peliculas.repository;

import ies.puesto.peliculas.model.Pelicula;

import java.util.List;
import java.util.Optional;

/**
 * interfaz PeliculaRepository
 */
public interface PeliculaRepository {

    /**
     * Lista de películas ordenadas por código.
     *
     * @return lista de películas
     */
    List<Pelicula> buscarTodos();

    /**
     * Busca una película por su código.
     * @param identificador de la película
     * @return la película
     */
    Optional<Pelicula> buscarPorCodigo(String identificador);

    /**
     * Inserta la película solo si su identificador
     * @param pelicula a insertar
     * @return true si se ha insertado, false si no
     */
    boolean insertarSiAusente(Pelicula pelicula);

    /**
     * Sustituye los datos de una película
     * @param pelicula pelicula
     * @return true si se ha actualizado,false si no existe
     */
    boolean actualizarSiExiste(Pelicula pelicula);

    /**
     * Borra una película.
     * @param identificador de la película
     * @return true si se ha borrado, false si no existía
     */
    boolean eliminar(String identificador);
}

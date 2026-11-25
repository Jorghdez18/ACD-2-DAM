package ies.puesto.peliculas.service;

import ies.puesto.peliculas.model.Pelicula;
import ies.puesto.peliculas.repository.PeliculaRepository;
import ies.puesto.peliculas.service.mapper.PeliculaDominioMapper;
import ies.puesto.peliculas.service.modelo.CreacionPelicula;
import ies.puesto.peliculas.service.modelo.EdicionPelicula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *Clase PeliculaService
 */
@Service
public class PeliculaService {

    private final PeliculaRepository repository;
    private final PeliculaDominioMapper mapper;

    public PeliculaService(PeliculaRepository repository, PeliculaDominioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Pelicula> listar() {
        return repository.buscarTodos();
    }

    public Optional<Pelicula> obtener(String codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    public Optional<Pelicula> crear(CreacionPelicula datos) {
        Pelicula pelicula = mapper.aPelicula(datos);
        if (repository.insertarSiAusente(pelicula)) {
            return Optional.of(pelicula);
        }
        return Optional.empty();
    }

    public Optional<Pelicula> actualizar(String codigo, EdicionPelicula datos) {
        Pelicula pelicula = mapper.aPelicula(codigo, datos);
        if (repository.actualizarSiExiste(pelicula)) {
            return Optional.of(pelicula);
        }
        return Optional.empty();
    }

    public boolean eliminar(String codigo) {
        return repository.eliminar(codigo);
    }
}

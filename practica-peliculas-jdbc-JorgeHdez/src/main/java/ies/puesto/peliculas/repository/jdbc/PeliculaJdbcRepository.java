package ies.puesto.peliculas.repository.jdbc;

import ies.puesto.peliculas.model.Pelicula;
import ies.puesto.peliculas.repository.PeliculaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase PeliculaJdbcRepository
 */
@Repository
@Profile("jdbc")
public class PeliculaJdbcRepository implements PeliculaRepository {

    private final JdbcTemplate jdbc;
    private final PeliculaRowMapper rowMapper;
    private final PeliculaEntityMapper entityMapper;

    PeliculaJdbcRepository(JdbcTemplate jdbc, PeliculaRowMapper rowMapper, PeliculaEntityMapper entityMapper) {
        this.jdbc = jdbc;
        this.rowMapper = rowMapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public List<Pelicula> buscarTodos() {
        String sql =
                "SELECT pelicula_codigo, titulo_texto, estreno_anio, duracion_minutos FROM peliculas";
        List<Pelicula> peliculas = new ArrayList<>();
        for (PeliculaEntity entity : jdbc.query(sql + " ORDER BY pelicula_codigo", rowMapper)) {
            peliculas.add(entityMapper.aPelicula(entity));
        }
        return peliculas;
    }

    @Override
    public Optional<Pelicula> buscarPorCodigo(String identificador) {
        String sql =
                "SELECT pelicula_codigo, titulo_texto, estreno_anio, duracion_minutos FROM peliculas";
        return jdbc.query(sql + " WHERE pelicula_codigo = ?", rowMapper, identificador)
                .stream()
                .findFirst()
                .map(entityMapper::aPelicula);
    }

    @Override
    public boolean insertarSiAusente(Pelicula pelicula) {
        PeliculaEntity entity = entityMapper.aEntity(pelicula);
        int filas = jdbc.update("INSERT INTO peliculas (pelicula_codigo, titulo_texto, estreno_anio, duracion_minutos) "
                        + "VALUES (?, ?, ?, ?) ON CONFLICT(pelicula_codigo) DO NOTHING",
                entity.getCodigoFila(), entity.getTituloFila(), entity.getAnioFila(), entity.getMinutosFila());
        return filas == 1;
    }

    @Override
    public boolean actualizarSiExiste(Pelicula pelicula) {
        PeliculaEntity entity = entityMapper.aEntity(pelicula);
        int filas = jdbc.update(
                "UPDATE peliculas SET titulo_texto = ?, estreno_anio = ?, duracion_minutos = ? "
                        + "WHERE pelicula_codigo = ?",
                entity.getTituloFila(), entity.getAnioFila(), entity.getMinutosFila(), entity.getCodigoFila());
        return filas == 1;
    }

    @Override
    public boolean eliminar(String identificador) {
        return jdbc.update("DELETE FROM peliculas WHERE pelicula_codigo = ?", identificador) == 1;
    }
}

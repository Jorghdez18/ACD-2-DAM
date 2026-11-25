package ies.puesto.peliculas.controller;

import ies.puesto.peliculas.controller.api.PeliculaEdicionRequest;
import ies.puesto.peliculas.controller.api.PeliculaRequest;
import ies.puesto.peliculas.controller.api.PeliculaResponse;
import ies.puesto.peliculas.controller.mapper.PeliculaApiMapper;
import ies.puesto.peliculas.model.Pelicula;
import ies.puesto.peliculas.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador PeliculaJdbcController
 */
@RestController
@Profile("jdbc")
@RequestMapping("/api/v1/jdbc/peliculas")
@Tag(name = "Películas JDBC")
public class PeliculaJdbcController {

    private static final String patronCodigo = "^PEL[0-9]{3}$";

    private final PeliculaService service;
    private final PeliculaApiMapper mapper;

    public PeliculaJdbcController(PeliculaService service, PeliculaApiMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Lista todas las películas.
     * @return lista de películas
     */
    @GetMapping
    @Operation(summary = "Listar todas las películas")
    public List<PeliculaResponse> listar() {
        return mapper.aRespuestas(service.listar());
    }

    /**
     * Obtiene una película
     * @param codigo código de la película
     * @return 200 con la película, o 404 con si no existe
     */
    @GetMapping("/{codigo}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "película encontrada"),
            @ApiResponse(responseCode = "400", description = "codsigo sin formato"),
            @ApiResponse(responseCode = "404", description = "no existe ninguna película con ese código")
    })
    public ResponseEntity<?> obtener(@PathVariable @Pattern(regexp = patronCodigo) String codigo) {
        Optional<Pelicula> pelicula = service.obtener(codigo);
        if (pelicula.isEmpty()) {
            return noExiste(codigo);
        }
        return ResponseEntity.ok(mapper.aRespuesta(pelicula.get()));
    }

    /**
     * Crea una película
     * @param peticion peticion de la pelicula
     * @return 201 con la película creada o 409 con si el código existe
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PeliculaRequest peticion) {
        Optional<Pelicula> creada = service.crear(mapper.aDatosCreacion(peticion));
        if (creada.isEmpty()) {
            ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                    "Ya existe una película con el código " + peticion.codigo());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.aRespuesta(creada.get()));
    }

    /**
     * Sustituye los datos de una película
     * @param codigo   código de la película
     * @param peticion peticion de la pelicula
     * @return 200 con la película actualizada, o 404 con si no existe
     */
    @PutMapping("/{codigo}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Película actualizada"),
            @ApiResponse(responseCode = "400", description = "Código o cuerpo inválido"),
            @ApiResponse(responseCode = "404", description = "No existe ninguna película con ese código")
    })
    public ResponseEntity<?> actualizar(@PathVariable @Pattern(regexp = patronCodigo) String codigo,
                                        @Valid @RequestBody PeliculaEdicionRequest peticion) {
        Optional<Pelicula> actualizada = service.actualizar(codigo, mapper.aDatosEdicion(peticion));
        if (actualizada.isEmpty()) {
            return noExiste(codigo);
        }
        return ResponseEntity.ok(mapper.aRespuesta(actualizada.get()));
    }

    /**
     * Borra una película
     * @param codigo código de la película
     * @return 204 si se ha borrado o 404 con  si no existe
     */
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable @Pattern(regexp = patronCodigo) String codigo) {
        if (service.eliminar(codigo)) {
            return ResponseEntity.noContent().build();
        }
        return noExiste(codigo);
    }
    private ResponseEntity<ProblemDetail> noExiste(String codigo) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "No existe película con el código " + codigo);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
    }
}

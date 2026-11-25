package aula.tareas.controller;

import aula.tareas.api.TareaRequest;
import aula.tareas.api.TareaResponse;
import aula.tareas.mapper.TareaMapperCsv;
import aula.tareas.modelo.Tarea;
import aula.tareas.service.ITareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/csv/tareas")
public class TareaControllerCsv {
    private final ITareaService servicio;
    private final TareaMapperCsv mapper;

    public TareaControllerCsv(@Qualifier("tareaServiceCsv") ITareaService servicio, TareaMapperCsv tareaMapperCsv) {
        this.servicio = servicio;
        this.mapper = tareaMapperCsv;
    }

    @GetMapping
    public List<TareaResponse> listar() {
       // return mapper.toEntity(servicio.listar());
        throw new IllegalArgumentException();


    }
    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> buscar(@PathVariable long id) {
        //return ResponseEntity.ofNullable(servicio.buscar(id));
        throw new IllegalArgumentException();


    }
    @PostMapping
    public ResponseEntity<TareaResponse> crear(@Valid @RequestBody TareaRequest request) {
        //TareaResponse tarea = servicio.crear(request.titulo());
        //return ResponseEntity.created(URI.create("/api/tareas/" + tarea.id())).body(tarea);
        throw new IllegalArgumentException();

    }

    public ResponseEntity<TareaResponse> actualizar(long id, TareaRequest request) {
        // TODO: Exponer la actualización y validar la petición.
        throw new UnsupportedOperationException("Pendiente de implementar");
    }

    public ResponseEntity<Void> eliminar(long id) {
        // TODO: Exponer el borrado y comunicar el resultado.
        throw new UnsupportedOperationException("Pendiente de implementar");
    }
}

package aula.tareas.mapper;

import aula.tareas.entity.TareaEntityJson;
import aula.tareas.modelo.Tarea;

/**
 * se puede hacer tambien añadiendo esto al principio:
 * @Component("tareaMapperJson")
 * y quitando el final y los static
 */
public final class TareaMapperJson {
    private TareaMapperJson() {}

    public static TareaEntityJson toEntity(Tarea tarea) {
        // TODO: Convertir al tipo de destino.
        throw new UnsupportedOperationException("Pendiente de implementar");
    }

    public static Tarea toDomain(TareaEntityJson entity) {
        // TODO: Convertir al tipo de destino.
        throw new UnsupportedOperationException("Pendiente de implementar");
    }
}

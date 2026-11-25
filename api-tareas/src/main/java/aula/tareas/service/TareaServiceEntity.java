package aula.tareas.service;

import aula.tareas.api.TareaRequest;
import aula.tareas.repository.ITareaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tareaServiceEntity")
public class TareaServiceEntity implements ITareaService{

    private final ITareaRepository tareaRepositoryEntity;

    public TareaServiceEntity(@Qualifier("repositoryEntity") ITareaRepository tareaRepositoryJson) {
        this.tareaRepositoryEntity = tareaRepositoryJson;
    }

    @Override
    public List<TareaRequest> listar() {
        return tareaRepositoryEntity.findAll();
    }

    @Override
    public TareaRequest buscar(long id) {
        return null;
    }

    @Override
    public TareaRequest crear(String titulo) {
        return tareaRepositoryEntity.create(titulo);
    }
}

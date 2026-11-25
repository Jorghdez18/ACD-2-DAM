package aula.tareas.repository;

import aula.tareas.api.TareaRequest;
import aula.tareas.entity.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositoryEntity")
public class TareaRepositoryEntity implements ITareaRepository { //necesitamos TareaReposityEntity/orm/... cuando trabajemos con bssd
    private final TareaRepositoryJpa tareaRepositoryJpa;

    public TareaRepositoryEntity(TareaRepositoryJpa tareaRepositoryJpa) {
        this.tareaRepositoryJpa = tareaRepositoryJpa;
    }


    @Override
    public TareaRequest create(String titulo) {
        TareaEntity tareaEntitySave = tareaRepositoryJpa.save(new TareaEntity(null, titulo));
        return null;
    }

    @Override
    public boolean delete(long identificador) {
        tareaRepositoryJpa.deleteById(identificador);
        return false;
    }

    @Override
    public List<TareaRequest> findAll() {
        return List.of();
    }

    @Override
    public TareaRequest findById(long identificador) {
        return null;
    }

    @Override
    public TareaRequest update(TareaRequest tarea) {
        return null;
    }
}

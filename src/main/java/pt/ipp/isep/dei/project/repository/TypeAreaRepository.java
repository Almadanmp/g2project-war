package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.TypeArea;

@Repository
public interface TypeAreaRepository extends CrudRepository<TypeArea, Long> {

    TypeArea findByName(String name);
}

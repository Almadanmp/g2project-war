package pt.ipp.isep.dei.project.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorTypeCrudRepo extends CrudRepository<SensorType, String> {

    Optional<SensorType> findByName(String name);

    List<SensorType> findAll();
}

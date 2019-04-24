package pt.ipp.isep.dei.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;

import java.util.List;

@Repository
public interface GeographicAreaRepository extends CrudRepository<GeographicArea, Long> {
    List<GeographicArea> findAll();
}

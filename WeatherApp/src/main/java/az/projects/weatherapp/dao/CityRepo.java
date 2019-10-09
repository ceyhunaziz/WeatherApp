package az.projects.weatherapp.dao;

import az.projects.weatherapp.dao.model.CityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepo extends CrudRepository<CityEntity,Long> {
    List<CityEntity> findAll();

    CityEntity findByCityName(String name);
}

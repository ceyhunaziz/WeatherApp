package az.projects.weatherapp.dao;

import az.projects.weatherapp.dao.model.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepo extends CrudRepository<WeatherEntity,Long> {
    List<WeatherEntity> findAllByDate(LocalDate date);
}

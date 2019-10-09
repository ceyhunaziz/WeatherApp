package az.projects.weatherapp.service;

import az.projects.weatherapp.dao.CityRepo;
import az.projects.weatherapp.dao.WeatherRepo;
import az.projects.weatherapp.dao.model.CityEntity;
import az.projects.weatherapp.dao.model.WeatherEntity;
import az.projects.weatherapp.exception.CityException;
import az.projects.weatherapp.mapper.WeatherMapper;
import az.projects.weatherapp.model.WeatherDto;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final CityRepo cityRepo;
    private final WeatherRepo weatherRepo;

    public WeatherService(CityRepo cityRepo, WeatherRepo weatherRepo) {
        this.cityRepo = cityRepo;
        this.weatherRepo = weatherRepo;
    }

    public void addWeatherInfo(Long id, WeatherDto weatherDto){
        CityEntity  cityEntity = cityRepo.findById(id)
                .orElseThrow(() -> new CityException("There is no city with Such ID"));

        WeatherEntity weatherEntity = WeatherMapper.INSTANCE.dtoToEntity(weatherDto);

        weatherEntity.setCityEntity(cityEntity);

        cityEntity.getWeatherEntityList().add(weatherEntity);

        cityRepo.save(cityEntity);
    }
}

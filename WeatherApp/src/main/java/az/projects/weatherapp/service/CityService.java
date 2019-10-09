package az.projects.weatherapp.service;

import az.projects.weatherapp.dao.CityRepo;
import az.projects.weatherapp.dao.WeatherRepo;
import az.projects.weatherapp.dao.model.CityEntity;
import az.projects.weatherapp.dao.model.WeatherEntity;
import az.projects.weatherapp.exception.CityException;
import az.projects.weatherapp.mapper.CityMapper;
import az.projects.weatherapp.mapper.WeatherMapper;
import az.projects.weatherapp.model.CityDto;
import az.projects.weatherapp.model.WeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityService.class);


    private final CityRepo cityRepo;
    private final WeatherRepo weatherRepo;

    public CityService(CityRepo cityRepo, WeatherRepo weatherRepo) {
        this.cityRepo = cityRepo;
        this.weatherRepo = weatherRepo;
    }

    public List<CityDto> getAllCities() {
        logger.info("Service/getAllCities Started");
        List<CityEntity> cityEntities = cityRepo.findAll();
        logger.info("Service/getAllCities Ended");
        return CityMapper.INSTANCE.cityListEntityToDto(cityEntities);
    }

    public List<CityEntity> getAllCityEntities(){
        logger.info("Service/getAllCityEntities Started");
        return cityRepo.findAll();
    }

    public CityDto getCityByName(String cityName) {

        CityEntity cityEntity = cityRepo.findByCityName(cityName);

        return CityMapper.INSTANCE.entityToDto(cityEntity);
    }

    public List<WeatherDto> getInfoByCityAndDate(String cityName, LocalDate date) {
        CityEntity cityEntity = cityRepo.findByCityName(cityName);

        List<WeatherEntity> weatherEntities = new ArrayList<>();

        for (WeatherEntity weatherEntity : cityEntity.getWeatherEntityList()) {
            if(weatherEntity.getDate().toString().equals(date.toString())){
                weatherEntities.add(weatherEntity);
            }
        }

        return WeatherMapper.INSTANCE.weatherListEntityToDto(weatherEntities);
    }

    public List<CityDto> getInfoByDate(LocalDate date) {

        List<CityDto> cityDtoList = new ArrayList<>();

        List<WeatherEntity> weatherEntityList = weatherRepo.findAllByDate(date);

        for (WeatherEntity weatherEntity : weatherEntityList) {
            WeatherDto weatherDto = WeatherDto.builder()
                    .temperature(weatherEntity.getTemperature())
                    .cityId(weatherEntity.getCityEntity().getCityId())
                    .date(date)
                    .build();

            cityDtoList.add(CityDto.builder()
                    .cityName(weatherEntity.getCityEntity().getCityName())
                    .weatherDtoList(Collections.singletonList(weatherDto))
                    .build());
        }

        return cityDtoList;
    }
}

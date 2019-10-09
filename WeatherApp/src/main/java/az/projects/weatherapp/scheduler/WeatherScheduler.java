package az.projects.weatherapp.scheduler;

import az.projects.weatherapp.dao.model.CityEntity;
import az.projects.weatherapp.model.WeatherDto;
import az.projects.weatherapp.service.CityService;
import az.projects.weatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class WeatherScheduler {

    private final CityService cityService;
    private final WeatherService weatherService;

    public WeatherScheduler(CityService cityService, WeatherService weatherService) {
        this.cityService = cityService;
        this.weatherService = weatherService;
    }

    private LocalDate date = LocalDate.now();

    private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);

    @Scheduled(cron = "0,10 * * * * *")
    public void cronJob(){
        logger.info("WeatherScheduler started!");

        List<CityEntity> cityEntities = cityService.getAllCityEntities();

        for (CityEntity cityEntity : cityEntities ) {

            Long id = cityEntity.getCityId();

            WeatherDto weatherDto = WeatherDto.builder()
                    .date(date)
                    .temperature(getRandom(10,30))
                    .cityId(id)
                    .build();
            weatherService.addWeatherInfo(id,weatherDto);
        }

        date = date.plusDays(1);
        logger.info("WeatherScheduler Ended!");
    }

    private Long getRandom(int from, int to){
        return ThreadLocalRandom.current().nextLong(from,to);
    }
}

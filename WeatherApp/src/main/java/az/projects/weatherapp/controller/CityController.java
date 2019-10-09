package az.projects.weatherapp.controller;

import az.projects.weatherapp.dao.CityRepo;
import az.projects.weatherapp.model.CityDto;
import az.projects.weatherapp.model.WeatherDto;
import az.projects.weatherapp.service.CityService;
import az.projects.weatherapp.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    @ApiOperation("Get All Cities")
    public ResponseEntity<List<CityDto>> getAllCities(){
        logger.info("Controller/GetAllCities Started");
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{cityName}")
    @ApiOperation("Get City By Name")
    public CityDto getCityByName(@PathVariable String cityName){
        logger.info("Controller/GetCityByName Started");
        return cityService.getCityByName(cityName);
    }

    @GetMapping("/DC")
    @ApiOperation("Get Info by City and Date")
    public List<WeatherDto> getInfoByCityAndDate(@RequestParam String cityName, @RequestParam("localDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return cityService.getInfoByCityAndDate(cityName,date);
    }

    @GetMapping("/date")
    @ApiOperation("Get Info by City and Date")
    public List<CityDto> getInfoByDate(@RequestParam("localDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return cityService.getInfoByDate(date);
    }

}

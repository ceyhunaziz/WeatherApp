package az.projects.weatherapp.mapper;

import az.projects.weatherapp.dao.model.WeatherEntity;
import az.projects.weatherapp.model.WeatherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class WeatherMapper {

    public static final WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
            @Mapping(target = "temperature",source = "temperature"),
            @Mapping(target = "date",source = "date"),
            @Mapping(target = "cityId",source = "cityEntity.cityId")
    })
    public abstract WeatherDto entityToDto(WeatherEntity weatherEntity);


    @Mappings({
            @Mapping(target = "weatherId",ignore = true),
            @Mapping(target = "cityEntity",ignore = true),
            @Mapping(target = "temperature",source = "temperature"),
            @Mapping(target = "date",source = "date"),
    })
    public abstract WeatherEntity dtoToEntity(WeatherDto weatherDto);

    public abstract List<WeatherDto> weatherListEntityToDto(List<WeatherEntity> weatherEntities);
}

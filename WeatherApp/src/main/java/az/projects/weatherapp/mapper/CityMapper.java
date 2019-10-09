package az.projects.weatherapp.mapper;

import az.projects.weatherapp.dao.model.CityEntity;
import az.projects.weatherapp.model.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = WeatherMapper.class)
public abstract class CityMapper {

    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mappings({
            @Mapping(target = "cityName",source = "cityName"),
            @Mapping(target = "weatherDtoList",source = "weatherEntityList")
    })
    public abstract CityDto entityToDto(CityEntity cityEntity);

    @Mappings({
            @Mapping(target = "cityId",ignore = true),
            @Mapping(target = "cityName",source = "cityName"),
            @Mapping(target = "weatherEntityList",ignore = true)
    })
    public abstract CityEntity dtoToEntity(CityDto cityDto);

    public abstract List<CityDto> cityListEntityToDto(List<CityEntity> cityEntities);
}

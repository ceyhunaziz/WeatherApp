package az.projects.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityDto {

    @NotBlank
    private String cityName;

    private List<WeatherDto> weatherDtoList;
}

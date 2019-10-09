package az.projects.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    @NotBlank
    private Long temperature;

    @NotBlank
    private LocalDate date;

    @Min(1)
    private Long cityId;
}

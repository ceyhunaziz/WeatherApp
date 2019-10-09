package az.projects.weatherapp.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "weather", schema = "public")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Long weatherId;

    @Column(name = "temperature")
    private Long temperature;

    @Column(name = "date")
    private LocalDate date;

    @JoinColumn(name = "city_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CityEntity cityEntity;
}

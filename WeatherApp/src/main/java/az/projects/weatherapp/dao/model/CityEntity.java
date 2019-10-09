package az.projects.weatherapp.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "city", schema = "public")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "cityEntity",fetch=FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeatherEntity> weatherEntityList;


}

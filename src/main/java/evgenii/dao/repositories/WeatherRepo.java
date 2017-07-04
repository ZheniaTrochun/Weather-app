package evgenii.dao.repositories;

import evgenii.model.WeatherHolder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhenia on 03.07.17.
 */
public interface WeatherRepo extends JpaRepository<WeatherHolder, Integer> {
    WeatherHolder findWeatherHolderByCity(String city);
}

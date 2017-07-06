package evgenii.service;

import evgenii.model.WeatherHolder;

/**
 * Created by zhenia on 06.07.17.
 */
public interface WeatherService {
    WeatherHolder getWeatherByCity(String city);
}

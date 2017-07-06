package evgenii.controller;

import evgenii.model.WeatherHolder;
import evgenii.service.WeatherService;
import evgenii.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhenia on 03.07.17.
 */

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/getWeather", method = RequestMethod.GET)
    @CrossOrigin(value = "${cross-origin}")
    public WeatherHolder getWeatherByCity(@RequestParam String city) {

        return weatherService.getWeatherByCity(city);
    }
}

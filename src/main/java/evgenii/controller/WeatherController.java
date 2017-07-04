package evgenii.controller;

import evgenii.model.WeatherHolder;
import evgenii.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhenia on 03.07.17.
 */

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(path = "/getWeather", method = RequestMethod.GET)
    @CrossOrigin(value = "http://localhost:3000")
    public WeatherHolder getWeatherByCity(@RequestParam String city) {

        return weatherService.getWeatherByCity(city);
    }
}

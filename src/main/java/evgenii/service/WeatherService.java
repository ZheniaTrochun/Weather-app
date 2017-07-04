package evgenii.service;

import com.sun.javafx.fxml.builder.URLBuilder;
import evgenii.dao.repositories.WeatherRepo;
import evgenii.model.WeatherHolder;
import evgenii.model.builders.WeatherHolderBuilder;
import evgenii.model.dto.WeatherApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class WeatherService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.access.key}")
    private String accessKey;

    @Value("${api.access.value}")
    private String accessValue;

    @Value("${api.weather.key}")
    private String weatherKey;

    @Autowired
    private WeatherRepo weatherRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);


    public WeatherHolder getWeatherByCity(String city) {
        LOGGER.info("Getting weather for city: " + city);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        WeatherHolder weatherHolder = weatherRepo.findWeatherHolderByCity(city);

        if ((weatherHolder == null) || (!weatherHolder.getDate().equals(date))) {

            URI request = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam(weatherKey, city)
                    .queryParam(accessKey, accessValue)
                    .build().toUri();

            WeatherApiResponseDto weatherFromApi = (new RestTemplate())
                    .getForObject(request, WeatherApiResponseDto.class);

            weatherHolder = (new WeatherHolderBuilder())
                    .city(city)
                    .temperature((int)(weatherFromApi.getMain().getTemp() - 272.15))
                    .generalWeather(weatherFromApi.getWeather().get(0).getDescription())
                    .date(date)
                    .windSpeed(weatherFromApi.getWind().getSpeed())
                    .windDeg(weatherFromApi.getWind().getDeg())
                    .build();

            LOGGER.info("Weather got from api");

            weatherRepo.save(weatherHolder);
        } else {
            LOGGER.info("Weather got from db");
        }

        return weatherHolder;
    }
}

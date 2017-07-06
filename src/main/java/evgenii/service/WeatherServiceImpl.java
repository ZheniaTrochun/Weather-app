package evgenii.service;

import evgenii.dao.repositories.WeatherRepo;
import evgenii.model.WeatherHolder;
import evgenii.model.builders.WeatherHolderBuilder;
import evgenii.model.dto.WeatherApiResponseDto;
import evgenii.property_holder.AppProperHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class WeatherServiceImpl implements WeatherService {

//    @Value("${api.url}")
//    private String apiUrl;
//
//    @Value("${api.access.key}")
//    private String accessKey;
//
//    @Value("${api.access.value}")
//    private String accessValue;
//
//    @Value("${api.weather.key}")
//    private String weatherKey;

    private final static int CORRECT_LIST_INDEX = 0;

    private final WeatherRepo weatherRepo;
    private final AppProperHolder appProperHolder;

    @Autowired
    public WeatherServiceImpl(WeatherRepo weatherRepo, AppProperHolder appProperHolder) {
        this.weatherRepo = weatherRepo;
        this.appProperHolder = appProperHolder;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);


    public WeatherHolder getWeatherByCity(String city) {
        LOGGER.info("Getting weather for city: " + city);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        WeatherHolder weatherHolder = weatherRepo.findWeatherHolderByCity(city);

        if ((weatherHolder == null) || (!weatherHolder.getDate().equals(date))) {

            URI request = UriComponentsBuilder.fromHttpUrl(appProperHolder.getUrl())
                    .queryParam(appProperHolder.getWeather().getKey(), city)
                    .queryParam(appProperHolder.getAccess().getKey(), appProperHolder.getAccess().getValue())
                    .build().toUri();

            WeatherApiResponseDto weatherFromApi = (new RestTemplate())
                    .getForObject(request, WeatherApiResponseDto.class);

            weatherHolder = (new WeatherHolderBuilder())
                    .city(city)
                    .temperature((int)(weatherFromApi.getMain().getTemp() - 272.15))
                    .generalWeather(weatherFromApi.getWeather().get(CORRECT_LIST_INDEX).getDescription())
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

package evgenii.model.builders;

import evgenii.model.WeatherHolder;

/**
 * Created by zhenia on 04.07.17.
 */
public class WeatherHolderBuilder {
    private String city;
    private double temperatureCelsius;
    private String generalWeather;
    private String date;
    private double windSpeed;
    private int windDeg;

    public WeatherHolderBuilder() {
    }

    public WeatherHolderBuilder city(String city) {
        this.city = city;
        return this;
    }

    public WeatherHolderBuilder temperature(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
        return this;
    }

    public WeatherHolderBuilder generalWeather(String generalWeather) {
        this.generalWeather = generalWeather;
        return this;
    }

    public WeatherHolderBuilder date(String date) {
        this.date = date;
        return this;
    }

    public WeatherHolderBuilder windSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public WeatherHolderBuilder windDeg(int windDeg) {
        this.windDeg = windDeg;
        return this;
    }

    public WeatherHolder build() {
        return new WeatherHolder(city, temperatureCelsius, generalWeather, date, windSpeed, windDeg);
    }
}

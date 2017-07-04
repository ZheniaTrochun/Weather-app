package evgenii.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhenia on 03.07.17.
 */

@Entity
public class WeatherHolder {

    @Id
    private String city;
    private double temperatureCelsius;
    private String generalWeather;
    private String date;
    private double windSpeed;
    private int windDeg;

    public WeatherHolder(String city,
                         double temperatureCelsius,
                         String generalWeather,
                         String date,
                         double windSpeed,
                         int windDeg) {

        this.city = city;
        this.temperatureCelsius = temperatureCelsius;
        this.generalWeather = generalWeather;
        this.date = date;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public WeatherHolder() {
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public String getGeneralWeather() {
        return generalWeather;
    }

    public void setGeneralWeather(String generalWeather) {
        this.generalWeather = generalWeather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherHolder{" +
                "city='" + city + '\'' +
                ", temperatureCelsius=" + temperatureCelsius +
                ", generalWeather='" + generalWeather + '\'' +
                ", date='" + date + '\'' +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                '}';
    }
}

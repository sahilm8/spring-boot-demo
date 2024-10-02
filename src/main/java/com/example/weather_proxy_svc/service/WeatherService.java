package com.example.weather_proxy_svc.service;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.weather_proxy_svc.model.Forecast;
import com.example.weather_proxy_svc.model.Response;
import com.example.weather_proxy_svc.model.Weather;
import com.example.weather_proxy_svc.util.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    public WeatherService(AppConfig appConfig, RestTemplate restTemplate) {
        this.appConfig = appConfig;
        this.restTemplate = restTemplate;
    }

    public String getWeatherData(
        String latitude,
        String longitude,
        boolean temperature,
        boolean relativeHumidity,
        boolean precipitation,
        boolean windSpeed,
        boolean feelsLike,
        boolean isDay,
        boolean rain,
        boolean showers,
        boolean snow,
        boolean weatherCode,
        boolean cloudCover,
        boolean pressure,
        boolean surfacePressure,
        boolean windDirection,
        boolean windGusts
    ) {
        String url = appConfig.getBaseUrl().concat("?latitude=%s&longitude=%s");
        StringBuilder reqUrl = new StringBuilder(String.format(url, latitude, longitude));

        logger.info("reqUrl: " + reqUrl);

        ArrayList<String> optionalParams = new ArrayList<>();
        if (temperature) optionalParams.add("temperature_2m");
        if (relativeHumidity) optionalParams.add("relative_humidity_2m");
        if (precipitation) optionalParams.add("precipitation");
        if (windSpeed) optionalParams.add("wind_speed_10m");
        if (feelsLike) optionalParams.add("apparent_temperature");
        if (isDay) optionalParams.add("is_day");
        if (rain) optionalParams.add("rain");
        if (showers) optionalParams.add("showers");
        if (snow) optionalParams.add("snowfall");
        if (weatherCode) optionalParams.add("weather_code");
        if (cloudCover) optionalParams.add("cloud_cover");
        if (pressure) optionalParams.add("pressure_msl");
        if (surfacePressure) optionalParams.add("surface_pressure");
        if (windDirection) optionalParams.add("wind_direction_10m");
        if (windGusts) optionalParams.add("wind_gusts_10m");

        if (!optionalParams.isEmpty()) {
            reqUrl.append("&current=").append(String.join(",", optionalParams));
            logger.info("reqUrl: " +   reqUrl);
        }

        try {
            Response response = restTemplate.getForObject(reqUrl.toString(), Response.class);
            return formatResponse(response);
        } catch (RestClientException e) {
            logger.error("Error fetching response: " + e.getMessage());
            return "Error fetching response";
        }
    }

    public String formatResponse(Response response) {
        final Forecast forecast = new Forecast();
        logger.info("Unformatted response: " + response);

        if (response.getLatitude() != null && response.getLongitude() != null) {
            forecast.setLatitude(response.getLatitude());
            forecast.setLongitude(response.getLongitude());
        }

        if (response.getCurrent() != null) {
        Weather current = response.getCurrent();
        if (current.getTime() != null) forecast.setTime(current.getTime());
        if (current.getTemperature_2m() != null) forecast.setTemperature(current.getTemperature_2m());
        if (current.getRelative_humidity_2m() != null) forecast.setRelativeHumidity(current.getRelative_humidity_2m());
        if (current.getPrecipitation() != null) forecast.setPrecipitation(current.getPrecipitation());
        if (current.getWind_speed_10m() != null) forecast.setWindSpeed(current.getWind_speed_10m());
        if (current.getApparent_temperature() != null) forecast.setFeelsLike(current.getApparent_temperature());
        if (current.getIs_day() != null) forecast.setIsDay(current.getIs_day());
        if (current.getRain() != null) forecast.setRain(current.getRain());
        if (current.getShowers() != null) forecast.setShowers(current.getShowers());
        if (current.getSnowfall() != null) forecast.setSnow(current.getSnowfall());
        if (current.getWeather_code() != null) forecast.setWeatherCode(current.getWeather_code());
        if (current.getCloud_cover() != null) forecast.setCloudCover(current.getCloud_cover());
        if (current.getPressure_msl() != null) forecast.setPressure(current.getPressure_msl());
        if (current.getSurface_pressure() != null) forecast.setSurfacePressure(current.getSurface_pressure());
        if (current.getWind_direction_10m() != null) forecast.setWindDirection(current.getWind_direction_10m());
        if (current.getWind_gusts_10m() != null) forecast.setWindGusts(current.getWind_gusts_10m());
    }

        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.info("Formatted response: " + mapper.writeValueAsString(forecast));
            return mapper.writeValueAsString(forecast);
        } catch (Exception e) {
            logger.error("Error formatting response: " + e.getMessage());
            return "Error formatting response";
        }
    }
}
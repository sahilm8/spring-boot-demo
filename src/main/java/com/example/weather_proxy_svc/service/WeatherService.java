package com.example.weather_proxy_svc.service;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.weather_proxy_svc.model.Forecast;
import com.example.weather_proxy_svc.model.Response;
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
        boolean windSpeed
    ) {
        String url = appConfig.getBaseUrl().concat("?latitude=%s&longitude=%s");
        StringBuilder reqUrl = new StringBuilder(String.format(url, latitude, longitude));

        logger.info("reqUrl: " + reqUrl);

        ArrayList<String> optionalParams = new ArrayList<>();
        if (temperature) optionalParams.add("temperature_2m");
        if (relativeHumidity) optionalParams.add("relative_humidity_2m");
        if (precipitation) optionalParams.add("precipitation");
        if (windSpeed) optionalParams.add("wind_speed_10m");

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
            if (response.getCurrent().getTime() != null) {
                forecast.setTime(response.getCurrent().getTime());
            }
            if (response.getCurrent().getTemperature_2m() != null) {
                forecast.setTemperature(response.getCurrent().getTemperature_2m());
            }
            if (response.getCurrent().getRelative_humidity_2m() != null) {
                forecast.setRelativeHumidity(response.getCurrent().getRelative_humidity_2m());
            }
            if (response.getCurrent().getPrecipitation() != null) {
                forecast.setPrecipitation(response.getCurrent().getPrecipitation());
            }
            if (response.getCurrent().getWind_speed_10m() != null) {
                forecast.setWindSpeed(response.getCurrent().getWind_speed_10m());
            }
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
package com.example.weather_proxy_svc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.weather_proxy_svc.util.AppConfig;

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
    ) throws Exception {
        final String url = appConfig.getBaseUrl().concat("?latitude=%s&longitude=%s");
        String reqUrl = String.format(url, latitude, longitude);

        if (temperature) {
            reqUrl += reqUrl.contains("&current=") ?
            ",temperature_2m"
            : "&current=temperature_2m";
        }
        if (relativeHumidity) {
            reqUrl += reqUrl.contains("&current=") ?
            ",relative_humidity_2m"
            : "&current=relative_humidity_2m";
        }
        if (precipitation) {
            reqUrl += reqUrl.contains("&current=") ?
            ",precipitation"
            : "&current=precipitation";
        }
        if (windSpeed) {
            reqUrl += reqUrl.contains("&current=") ?
                ",wind_speed_10m"
                : "&current=wind_speed_10m";
        }

        String response = restTemplate.getForObject(reqUrl, String.class);
        if (response == null) {
            logger.info("Invalid request");
            throw new RestClientException("Invalid request, please refer to the README at https://github.com/sahilm8/weather_proxy_svc");
        }
        formatResponse(response);

        return response;
    }
    public String formatResponse(String response) {
        logger.info("Response: " + response);
        return null;
    }
}
package com.example.weather_proxy_svc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherData(String latitude, String longitude) throws Exception {
        String endpoint =
            "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current=temperature_2m,relative_humidity_2m,precipitation,wind_speed_10m";
            String response = restTemplate.getForObject(String.format(endpoint, latitude, longitude), String.class);
            logger.info("Response from OpenMeteo API: " + response);
            if (response == null) {
 		        logger.info("Invalid request");
                throw new RestClientException("Invalid request. Please refer to the documentation at https://github.com/sahilm8/weather_proxy_svc");
            }
            return response;
    }
}
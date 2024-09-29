package com.example.weather_proxy_svc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather_proxy_svc.service.WeatherService;

@RestController
public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String index() {
		logger.info("Request to root");
		return String.format(
			"Welcome to the Weather Proxy Service!%n%n" +
			"The API endpoint /v1/weather_proxy accepts GET requests with the following parameters:%n" +
			"- latitude (required)%n" +
			"- longitude (required)"
		);
	}

	@GetMapping(value = "/v1/weather_proxy", produces = MediaType.TEXT_PLAIN_VALUE)
	public String weatherProxy(
			@RequestParam(value = "latitude", defaultValue = "") String latitude,
			@RequestParam(value = "longitude", defaultValue = "") String longitude
		) {
		logger.info("Request to /v1/weather_proxy with params latitude: " + latitude + ", longitude: " + longitude);
		try {
			return weatherService.getWeatherData(latitude, longitude);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping(value = "/*", produces = MediaType.TEXT_PLAIN_VALUE)
	public String invalid() {
		logger.info("Invalid request");
		return "Invalid request. Please refer to the documentation at https://github.com/sahilm8/weather_proxy_svc";
	}
}
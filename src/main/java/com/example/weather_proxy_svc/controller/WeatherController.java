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
	private static final String proxyEndpoint = "/v1/weather_proxy";

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String index() {
		logger.info("Request to root");
		return String.format(
			"Welcome to the Weather Proxy Service!%n%n" +
			"The API endpoint /v1/weather_proxy accepts GET requests with the following parameters:%n" +
			"- String latitude (required)%n" +
			"- String longitude (required)%n" +
			"- boolean temperature (optional)%n" +
			"- boolean relativeHumidity (optional)%n" +
			"- boolean precipitation (optional)%n" +
			"- boolean windSpeed (optional)%n"
		);
	}

	// http://localhost:3000/v1/weather_proxy?latitude=51.5085&longitude=-0.1257&temperature=true&relativeHumidity=true&precipitation=true&windSpeed=true&feelsLike=true&isDay=true&rain=true&showers=true&snow=true&weatherCode=true&cloudCover=true&pressure=true&surfacePressure=true&windDirection=true&windGusts=true
	@GetMapping(value = proxyEndpoint, produces = MediaType.TEXT_PLAIN_VALUE)
	public String weatherProxy(
		@RequestParam(value = "latitude", defaultValue = "51.5085") String latitude,
		@RequestParam(value = "longitude", defaultValue = "-0.1257") String longitude,
		@RequestParam(required = false) boolean temperature,
		@RequestParam(required = false) boolean relativeHumidity,
		@RequestParam(required = false) boolean precipitation,
		@RequestParam(required = false) boolean windSpeed,
		@RequestParam(required = false) boolean feelsLike,
		@RequestParam(required = false) boolean isDay,
		@RequestParam(required = false) boolean rain,
		@RequestParam(required = false) boolean showers,
		@RequestParam(required = false) boolean snow,
		@RequestParam(required = false) boolean weatherCode,
		@RequestParam(required = false) boolean cloudCover,
		@RequestParam(required = false) boolean pressure,
		@RequestParam(required = false) boolean surfacePressure,
		@RequestParam(required = false) boolean windDirection,
		@RequestParam(required = false) boolean windGusts
	) { 
		logger.info("Request to /v1/weather_proxy with latitude: " + latitude + " & longitude: " + longitude);
		return weatherService.getWeatherData(
			latitude,
			longitude,
			temperature,
			relativeHumidity,
			precipitation,
			windSpeed,
			feelsLike,
			isDay,
			rain,
			showers,
			snow,
			weatherCode,
			cloudCover,
			pressure,
			surfacePressure,
			windDirection,
			windGusts
		);
	}

	@GetMapping(value = "/*", produces = MediaType.TEXT_PLAIN_VALUE)
	public String invalid() {
		logger.info("Invalid request");
		return "Invalid request, please refer to the README at https://github.com/sahilm8/weather_proxy_svc";
	}
}
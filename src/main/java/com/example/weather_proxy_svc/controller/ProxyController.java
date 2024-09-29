package com.example.weather_proxy_svc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

	private static final String template = "Hello, %s!";

	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String index() {
		return String.format(
			"Welcome to the Weather Proxy Service!%n%n" +
			"The API endpoint /v1/weather_proxy accepts GET requests with the following parameters:%n" +
			"- latitude (required)%n" +
			"- longitude (required)"
		);
	}

	@GetMapping(value = "/v1/weather_proxy", produces = MediaType.TEXT_PLAIN_VALUE)
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}
}
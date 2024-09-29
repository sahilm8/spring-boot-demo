package com.example.weather_proxy_svc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

	private static final String template = "Hello, %s!";

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format(template, name);
	}
}
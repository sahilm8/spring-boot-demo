package com.example.weather_proxy_svc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${open.meteo.base.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return this.baseUrl;
    }
}
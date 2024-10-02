package com.example.weather_proxy_svc.model;

import lombok.Data;

@Data
public class Forecast {
    private Double latitude;
    private Double longitude;
    private String time;
    private Double temperature;
    private Double relativeHumidity;
    private Double precipitation;
    private Double windSpeed;
}
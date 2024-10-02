package com.example.weather_proxy_svc.model;

import lombok.Data;

@Data
public class Units {
    private String time;
    private String interval;
    private String temperature_2m;
    private String relative_humidity_2m;
    private String precipitation;
    private String wind_speed_10m;
}
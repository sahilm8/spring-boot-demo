package com.example.weather_proxy_svc.model;

import lombok.Data;

@Data
public class Weather {
    private String time;
    private long interval;
    private Double temperature_2m;
    private Double relative_humidity_2m;
    private Double precipitation;
    private Double wind_speed_10m;
}
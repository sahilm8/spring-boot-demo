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
    private String apparent_temperature;
    private String is_day;
    private String rain;
    private String showers;
    private String snowfall;
    private String weather_code;
    private String cloud_cover;
    private String pressure_msl;
    private String surface_pressure;
    private String wind_direction_10m;
    private String wind_gusts_10m;
}
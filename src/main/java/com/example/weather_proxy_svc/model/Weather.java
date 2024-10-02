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
    private Double apparent_temperature;
    private Integer is_day;
    private Double rain;
    private Double showers;
    private Double snowfall;
    private Integer weather_code;
    private Integer cloud_cover;
    private Double pressure_msl;
    private Double surface_pressure;
    private Integer wind_direction_10m;
    private Double wind_gusts_10m;
}
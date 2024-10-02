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
    private Double feelsLike;
    private Integer isDay;
    private Double rain;
    private Double showers;
    private Double snow;
    private Integer weatherCode;
    private Integer cloudCover;
    private Double pressure;
    private Double surfacePressure;
    private Integer windDirection;
    private Double windGusts;
}
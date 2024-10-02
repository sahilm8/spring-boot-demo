package com.example.weather_proxy_svc.model;

import lombok.Data;

@Data
public class Response {
    private Double latitude;
    private Double longitude;
    private Double generationtime_ms;
    private long utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private Double elevation;
    private Units current_units;
    private Weather current;
}
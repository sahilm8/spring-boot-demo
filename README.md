# Weather Proxy Service

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)

A proxy serivce returning weather data from [Open-Meteo](https://open-meteo.com).

## Usage

By default, the service starts on port 3000. The API endpoint `/v1/weather_proxy` accepts GET requests with the following parameters:

- String latitude (required)
- String longitude (required)
- boolean temperature (optional)
- boolean relativeHumidity (optional)
- boolean percipitation (optional)
- boolean windSpeed (optional)
- boolean feelsLike (optional)
- boolean isDay (optional)
- boolean rain (optional)
- boolean showers (optional)
- boolean snow (optional)
- boolean weatherCode (optional)
- boolean cloudCover (optional)
- boolean pressure (optional)
- boolean surfacePressure (optional)
- boolean windDirection (optional)
- boolean windGusts (optional)

```
curl "http://localhost:3000/v1/weather_proxy?latitude=51.5085&longitude=-0.1257&temperature=true&relativeHumidity=true&precipitation=true&windSpeed=true&feelsLike=true&isDay=true&rain=true&showers=true&snow=true&weatherCode=true&cloudCover=true&pressure=true&surfacePressure=true&windDirection=true&windGusts=true"
```

## Stack

- Java 21
- Spring Boot

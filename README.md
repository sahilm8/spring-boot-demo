# Weather Proxy Service

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)

A proxy serivce returning weather data from [Open-Meteo](https://open-meteo.com).

## Usage

By default, the service starts on port 8080. The API endpoint `/v1/weather_proxy` accepts GET requests with the following parameters:

- latitude (required)
- longitude (required)

## Stack

- Java 21
- Spring Boot
- Spring Data JPA

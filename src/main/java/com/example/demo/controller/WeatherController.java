package com.example.demo.controller;

import com.example.demo.entity.Weather;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<Weather> showWeather(@RequestParam String gridX, @RequestParam String gridY) {
        Weather weather = weatherService.getWeather(gridX, gridY);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/api/weather/latest")
    public ResponseEntity<Weather> getLatestWeather() {
        Weather weather = weatherService.getLatestWeather();
        return ResponseEntity.ok(weather);
    }
}

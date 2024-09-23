package com.example.demo.controller;

import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // 하루 동안의 시간대별 날씨 데이터를 가져오는 엔드포인트
    @GetMapping("/weather/daily")
    public ResponseEntity<List<String>> getDailyWeather(@RequestParam int gridX, @RequestParam int gridY) {
        List<String> dailyWeatherData = WeatherService.getDailyWeather(gridX, gridY);

        return ResponseEntity.ok(dailyWeatherData);
    }
}

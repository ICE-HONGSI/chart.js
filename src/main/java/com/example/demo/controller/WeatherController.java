package com.example.demo.controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.service.MapService;
import com.example.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MapService mapService;

    // 하루 동안의 시간대별 날씨 데이터를 가져오는 엔드포인트
    @GetMapping("/weather")
    public ResponseEntity<List<String>> getDailyWeather(@RequestParam int gridX, @RequestParam int gridY) {
        List<String> dailyWeatherData = weatherService.getDailyWeather(gridX, gridY);

        return ResponseEntity.ok(dailyWeatherData);
    }
    @GetMapping("/weather/by-address")
    public ResponseEntity<List<String>> getDailyWeatherByAddress(@RequestParam String address) {
        // MapService에서 주소로 좌표(x, y) 받아오기
        Map<String, String> xy = mapService.getMapAddress(address);

        // 소수점 값을 반올림하여 int로 변환
        int x = (int) Math.round(Double.parseDouble(xy.get("x")));
        int y = (int) Math.round(Double.parseDouble(xy.get("y")));

        // 좌표를 이용하여 WeatherService에서 날씨 정보 받아오기
        List<String> dailyWeatherData = weatherService.getDailyWeather(y, x);

        return ResponseEntity.ok(dailyWeatherData);
    }


    // 좌표로 날씨 데이터를 가져오는 엔드포인트
    @GetMapping("/weather/chart-data")
    public ResponseEntity<List<String[]>> getWeatherChartData(@RequestParam String address) {
        // MapService에서 주소로 좌표(x, y) 받아오기
        Map<String, String> xy = mapService.getMapAddress(address);
        int x = (int) Math.round(Double.parseDouble(xy.get("x")));
        int y = (int) Math.round(Double.parseDouble(xy.get("y")));

        // 좌표를 이용하여 WeatherService에서 날씨 정보 받아오기
        List<String[]> dailyWeatherData = weatherService.getDailyWeatherAsArray(y, x);

        return ResponseEntity.ok(dailyWeatherData);  // JSON 형태로 반환
    }
    @GetMapping("/weather/chart")
    public String showWeatherChartPage() {
        return "weatherChart";  // weatherChart.html 페이지를 렌더링
    }
}

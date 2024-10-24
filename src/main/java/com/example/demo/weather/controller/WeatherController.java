package com.example.demo.weather.controller;

import com.example.demo.weather.service.MapService;
import com.example.demo.weather.service.WeatherService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
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

    // CSV 파일로 날씨 데이터를 추출하는 엔드포인트
    @GetMapping("/weather/download-csv")
    public void downloadCsv(@RequestParam String address, HttpServletResponse response) throws IOException {
        // MapService에서 주소로 좌표(x, y) 받아오기
        Map<String, String> xy = mapService.getMapAddress(address);
        int x = (int) Math.round(Double.parseDouble(xy.get("x")));
        int y = (int) Math.round(Double.parseDouble(xy.get("y")));
        // 좌표를 이용하여 WeatherService에서 날씨 정보 받아오기
        List<String[]> weatherData = weatherService.getDailyWeatherAsArray(y, x);

        // CSV 파일 응답 설정
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"weather-data.csv\"");

        // CSV 파일 쓰기
        PrintWriter writer = response.getWriter();
        writer.println("Time,Temperature,Humidity");  // CSV 헤더 작성

        for (String[] data : weatherData) {
            writer.println(String.join(",", data[0], data[1], data[2]));  // CSV 각 행 작성
        }

        writer.flush();
        writer.close();
    }
}

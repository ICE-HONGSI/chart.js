package com.example.demo.service;

import com.example.demo.entity.Weather;
import com.example.demo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    private final String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private final String serviceKey = "69YYtu1XspY1rFpEKlo8VJ5mQhO8ab%2BldbDFTsuz7QuxRAucG6e%2BpiDonS0dCWh%2B7V8Mw7cOTXHaFC%2Fs5%2BOuzQ%3D%3D";  // 이미 인코딩된 서비스 키

    // 매 1시간마다 실행
    @Scheduled(fixedRate = 3600000)
    public void fetchAndStoreWeatherData() {
        try {
            String nx = "55";  // 예시 좌표
            String ny = "127";
            LocalDateTime now = LocalDateTime.now();
            String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));  // 예시: 20240912
            String baseTime = "0500";  // 예시: 0500
            String dataType = "json";  // json 데이터 형식
            String pageNo = "1";
            String numOfRows = "1000";

            // URL 생성
            String url = buildUrl(nx, ny, baseDate, baseTime, dataType, pageNo, numOfRows);
            System.out.println("Generated URL: " + url);

            // OpenAPI 호출
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String weatherData = response.getBody();
                if (weatherData != null && !weatherData.contains("SERVICE_KEY_IS_NOT_REGISTERED_ERROR")) {
                    saveWeatherData(weatherData);
                } else {
                    System.err.println("API 오류: " + weatherData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // URL 생성 메서드
    public String buildUrl(String nx, String ny, String baseDate, String baseTime, String dataType, String pageNo, String numOfRows) {
        return apiUrl +
                "?serviceKey=" + "69YYtu1XspY1rFpEKlo8VJ5mQhO8ab%2BldbDFTsuz7QuxRAucG6e%2BpiDonS0dCWh%2B7V8Mw7cOTXHaFC%2Fs5%2BOuzQ%3D%3D" +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows +
                "&dataType=" + dataType +
                "&base_date=" + baseDate +
                "&base_time=" + baseTime +
                "&nx=" + nx +
                "&ny=" + ny;
    }

    // 받은 데이터를 DB에 저장하는 메서드
    private void saveWeatherData(String jsonData) {
        Weather weather = new Weather();
        weather.setTime(LocalDateTime.now().toString());
        weather.setJsonData(jsonData);
        weatherRepository.save(weather);
    }

    // 실시간 API 데이터를 가져오는 메서드 (요청 시 호출)
    public Weather getWeather(String nx, String ny) {
        try {
            LocalDateTime now = LocalDateTime.now();
            String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String baseTime = "0500";  // 예시 시간
            String dataType = "json";
            String pageNo = "1";
            String numOfRows = "1000";

            String url = buildUrl(nx, ny, baseDate, baseTime, dataType, pageNo, numOfRows);
            System.out.println("Generated URL: " + url);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

                String weatherData = response.getBody();
                    Weather weather = new Weather();
                    weather.setJsonData(weatherData);
                    weather.setTime(LocalDateTime.now().toString());
                    return weather;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching weather data");
        }
    }

    // 최신 날씨 데이터를 가져오는 메서드
    public Weather getLatestWeather() {
        return weatherRepository.findTopByOrderByIdDesc();
    }
}

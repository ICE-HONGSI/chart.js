package com.example.demo.chart.controller;

import com.example.demo.chart.service.ChartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63342")
public class ChartDataController {

    @Autowired
    private ChartDataService chartDataService;

    @GetMapping("/chart-data1")
    public ResponseEntity<Map<String, Object>> getMonthlyData() {
        return chartDataService.getMonthlyData();
    }

    @GetMapping("/chart-data2")
    public ResponseEntity<Map<String, Object>> getBodyPartsData() {
        return chartDataService.getBodyPartsData();
    }

    @GetMapping("/chart-data3")
    public ResponseEntity<Map<String, Object>> getAgeData() {
        return chartDataService.getAgeData();
    }
}

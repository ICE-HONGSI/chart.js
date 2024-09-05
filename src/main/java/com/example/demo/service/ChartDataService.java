package com.example.demo.service;

import com.example.demo.dto.ChartDataDTO;
import com.example.demo.entity.MonthlyData;
import com.example.demo.entity.BodyPartsData;
import com.example.demo.entity.AgeData;
import com.example.demo.repository.MonthlyDataRepository;
import com.example.demo.repository.BodyPartsDataRepository;
import com.example.demo.repository.AgeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChartDataService {

    @Autowired
    private MonthlyDataRepository monthlyDataRepository;

    @Autowired
    private BodyPartsDataRepository bodyPartsDataRepository;

    @Autowired
    private AgeDataRepository ageDataRepository;

    public ResponseEntity<Map<String, Object>> getMonthlyData() {
        List<MonthlyData> dataList = monthlyDataRepository.findAll();
        List<ChartDataDTO> dataDTOs = dataList.stream()
                .map(data -> new ChartDataDTO(data.getValue()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("data", dataDTOs));
    }

    public ResponseEntity<Map<String, Object>> getBodyPartsData() {
        List<BodyPartsData> dataList = bodyPartsDataRepository.findAll();
        List<ChartDataDTO> dataDTOs = dataList.stream()
                .map(data -> new ChartDataDTO(data.getCount()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("data", dataDTOs));
    }

    public ResponseEntity<Map<String, Object>> getAgeData() {
        List<AgeData> dataList = ageDataRepository.findAll();
        List<ChartDataDTO> dataDTOs = dataList.stream()
                .map(data -> new ChartDataDTO(data.getPopulation()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("data", dataDTOs));
    }
}

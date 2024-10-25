package com.example.demo.bms.service;

import com.example.demo.bms.dto.BmsDTO;
import com.example.demo.bms.repository.BmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsService {
    @Autowired
    private BmsRepository bmsRepository;

    public List<BmsDTO> getBmsData() {
        return bmsRepository.findAll().stream().map(BmsDTO::new).collect(Collectors.toList());
    }

    public BmsDTO getBmsById(Long rackId) {
        return bmsRepository.findById(rackId)
                .map(BmsDTO::new)
                .orElseThrow(() -> new RuntimeException("BMS 데이터가 존재하지 않습니다."));
    }

}

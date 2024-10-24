package com.example.demo.bms.service;

import com.example.demo.bms.dto.SysDTO;
import com.example.demo.bms.repository.SysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysService {

    @Autowired
    private SysRepository sysRepository;

    public List<SysDTO> getSysData() {
        return sysRepository.findAll().stream().map(SysDTO::new).collect(Collectors.toList());
    }
}
package com.example.demo.bms.controller;

import com.example.demo.bms.dto.BmsDTO;
import com.example.demo.bms.service.BmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bms")
public class BmsRestController {

    @Autowired
    private BmsService bmsService;

    @GetMapping("/rack/{rackId}")
    public BmsDTO getBmsDetail(@PathVariable Long rackId) {
        return bmsService.getBmsById(rackId);
    }
}

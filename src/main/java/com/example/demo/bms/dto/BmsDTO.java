package com.example.demo.bms.dto;

import com.example.demo.bms.entity.Bms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmsDTO {
    private Long id;
    private String date;
    private double voltage;
    private double current;
    private double soc;
    private double temperature;
    private double averageVoltage;
    private double maxVoltage;
    private double minVoltage;
    private boolean alarm;
    private String state;
    private String detailInfo;
    private String tracingInfo;

    // Constructor to convert from Entity to DTO
    public BmsDTO(Bms bms) {
        this.id = bms.getId();
        this.date = bms.getDate();
        this.voltage = bms.getVoltage();
        this.current = bms.getCurrent();
        this.soc = bms.getSoc();
        this.temperature = bms.getTemperature();
        this.averageVoltage = bms.getAverageVoltage();
        this.maxVoltage = bms.getMaxVoltage();
        this.minVoltage = bms.getMinVoltage();
        this.alarm = bms.isAlarm();
        this.state = bms.getState();
        this.detailInfo = bms.getDetailInfo();
        this.tracingInfo = bms.getTracingInfo();
    }

}

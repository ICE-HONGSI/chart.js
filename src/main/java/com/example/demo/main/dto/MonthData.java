package com.example.demo.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MonthData {
    private int year;
    private int month;
    private int day;
    private double chargeAmount;
    private double dischargeAmount;
    private double powerGeneration;
    private double totalCharge;
    private double totalDischarge;
    private double totalPowerGeneration;

    public MonthData(double totalCharge, double totalDischarge, double totalPowerGeneration) {
        this.totalCharge = totalCharge;
        this.totalDischarge = totalDischarge;
        this.totalPowerGeneration = totalPowerGeneration;
    }

    public MonthData(int year, int month, double totalCharge, double totalDischarge, double totalPowerGeneration) {
        this.year = year;
        this.month = month;
        this.totalCharge = totalCharge;
        this.totalDischarge = totalDischarge;
        this.totalPowerGeneration = totalPowerGeneration;
    }
}

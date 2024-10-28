package com.example.demo.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyData {
    private int year;
    private int month;
    private int day;
    private double chargeAmount;
    private double dischargeAmount;
    private double powerGeneration;
    private double totalCharge;
    private double totalDischarge;
    private double totalPowerGeneration;
    private double avgChargeAmount;
    private double avgDischargeAmount;
    private double avgPowerGeneration;


    public DailyData(double totalCharge, double totalDischarge, double totalPowerGeneration ,int year, int month, int day) {
        this.totalCharge = totalCharge;
        this.totalDischarge = totalDischarge;
        this.totalPowerGeneration = totalPowerGeneration;
    }
//    public DailyData(int year, int month, int day, double avgChargeAmount, double avgDischargeAmount, double avgPowerGeneration) {
//        this.year = year;
//        this.month = month;
//        this.day = day;
//        this.avgChargeAmount = avgChargeAmount;
//        this.avgDischargeAmount = avgDischargeAmount;
//        this.avgPowerGeneration = avgPowerGeneration;
//    }
}

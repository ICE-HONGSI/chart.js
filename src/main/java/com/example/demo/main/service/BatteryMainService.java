//package com.example.demo.service;
//
//import com.example.demo.entity.Battery;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface BatteryMainService {
//    List<Battery> getAllBatteryData();
//    List<Battery> getSummarizedBatteryData();
//}
package com.example.demo.main.service;

import com.example.demo.main.dto.DailyData;
import com.example.demo.main.dto.MonthData;
import com.example.demo.main.entity.Battery;

import java.util.List;

public interface BatteryMainService {
    List<Battery> getAllBatteryData();
    List<Battery> getTodayBatteryData();

    DailyData getDayData(int year, int month, int day);

    List<Battery> getHourlyData(int year, int month, int day);

//    List<DailyData> getReportsForMonth(int year, int month);

    MonthData getMonthData(int year, int month);
}
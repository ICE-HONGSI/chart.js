//package com.example.demo.service;
//
//import com.example.demo.entity.Battery;
//import com.example.demo.repository.BatteryMainRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class BatteryMainServiceImpl implements BatteryMainService {
//
//    private final BatteryMainRepository batteryMainRepository;
//
//    @Autowired
//    public BatteryMainServiceImpl(BatteryMainRepository batteryMainRepository) {
//        this.batteryMainRepository = batteryMainRepository;
//    }
//
//    @Override
//    public List<Battery> getAllBatteryData() {
//        return batteryMainRepository.findAll();
//    }
//
//    @Override
//    public List<Battery> getSummarizedBatteryData() {
//        return batteryMainRepository.findSummarizedByHour();
//    }
//}
package com.example.demo.main.service;

import com.example.demo.main.dto.DailyData;
import com.example.demo.main.dto.MonthData;
import com.example.demo.main.entity.Battery;
import com.example.demo.main.repository.BatteryMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatteryMainServiceImpl implements BatteryMainService {

    private final BatteryMainRepository batteryMainRepository;

    @Autowired
    public BatteryMainServiceImpl(BatteryMainRepository batteryMainRepository) {
        this.batteryMainRepository = batteryMainRepository;
    }

    @Override
    public List<Battery> getAllBatteryData() {
        return batteryMainRepository.findAll();
    }

    @Override
    public List<Battery> getTodayBatteryData() {
//        LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.of(2024,10,24);
        return batteryMainRepository.findTodayBatteryData(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
    }
    @Override
    public DailyData getDayData(int year, int month, int day) {
        // 주어진 날짜에 해당하는 데이터를 조회
        List<Battery> dailyDataList = batteryMainRepository.findByDate(year, month, day);

        double totalCharge = 0;
        double totalDischarge = 0;
        double totalPowerGeneration = 0;

        // 데이터를 합산
        for (Battery battery : dailyDataList) {
            totalCharge += battery.getCharge_amount();
            totalDischarge += battery.getDischarge_amount();
            totalPowerGeneration += battery.getPower_generation();
        }

        return new DailyData(
                totalCharge, // 하루 충전량
                totalDischarge, // 하루 방전량
                totalPowerGeneration, // 하루 발전량
                year,        // 연도
                month,       // 월
                day         // 일
        );
    }


    @Override
    public List<Battery> getHourlyData(int year, int month, int day) {
        return batteryMainRepository.findByDate(year, month, day);
    }

//    @Override
//    public List<DailyData> getReportsForMonth(int year, int month) {
//        return batteryMainRepository.findByYearAndMonth(year, month);
//    }

    @Override
    public MonthData getMonthData(int year, int month) {
        double totalCharge = 0;
        double totalDischarge = 0;
        double totalPowerGeneration = 0;
        List<Battery> monthlyData = batteryMainRepository.findByMonth(year, month);
        // 해당 월의 모든 일자에 대해 쿼리 실행
        for (Battery battery : monthlyData) {
            totalCharge += battery.getCharge_amount();
            totalDischarge += battery.getDischarge_amount();
            totalPowerGeneration += battery.getPower_generation();
        }
        return new MonthData(
                year,
                month,
                totalCharge,
                totalDischarge,
                totalPowerGeneration
        );
    }

}
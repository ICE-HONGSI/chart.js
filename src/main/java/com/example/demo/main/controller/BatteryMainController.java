//package com.example.demo.controller;
//
//import com.example.demo.entity.Battery;
//import com.example.demo.service.BatteryMainService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import java.util.List;
//
//@Controller
//public class BatteryMainController {
//
//    private final BatteryMainService batteryMainService;
//
//    @Autowired
//    public BatteryMainController(BatteryMainService batteryMainService) {
//        this.batteryMainService = batteryMainService;
//    }
//
//    @GetMapping("/api/batteryData")
//    @ResponseBody
//    public List<Battery> getBatteryData() {
//        return batteryMainService.getSummarizedBatteryData();
//    }
//}
package com.example.demo.main.controller;

import com.example.demo.main.dto.DailyData;
import com.example.demo.main.dto.MonthData;
import com.example.demo.main.entity.Battery;
import com.example.demo.main.service.BatteryMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Month;
import java.util.List;

@Controller
public class BatteryMainController {

    private final BatteryMainService batteryMainService;

    @Autowired
    public BatteryMainController(BatteryMainService batteryMainService) {
        this.batteryMainService = batteryMainService;
    }

    // REST API 추가
    // REST API 수정: 오늘의 데이터만 반환
    @GetMapping("/api/batteryData")
    @ResponseBody
    public List<Battery> getBatteryData() {
        return batteryMainService.getTodayBatteryData();
    }

//    @GetMapping("/api/dailyData/{year}/{month}")
//    @ResponseBody
//    public List<MonthData> getDailyData(@PathVariable int year, @PathVariable int month) {
//        return batteryMainService.getDailyData(year, month);
//    }

//    @GetMapping("/api/dailyData/{year}/{month}/{day}")
//    @ResponseBody
//    public List<Battery> getDailyData(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
//        return batteryMainService.getHourlyData(year, month, day);
//    }

//    @GetMapping("/api/batteries")
//    @ResponseBody
//    public List<DailyData> getBatteryReports(@RequestParam int year, @RequestParam int month) {
//        return batteryMainService.getReportsForMonth(year, month);
//    }
}
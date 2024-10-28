package com.example.demo.main.controller;

import com.example.demo.main.dto.DailyData;
import com.example.demo.main.dto.MonthData;
import com.example.demo.main.entity.Battery;
import com.example.demo.main.service.BatteryMainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BatteryMainServiceImpl batteryMainService;

    @GetMapping("/")
    public String home(Model model) {
        MonthData monthData = batteryMainService.getMonthData(2024,10);
        List<Battery> dailyDatas = batteryMainService.getHourlyData(2024,10,24);
        int totalCharge = 0, totalDischarge = 0, totalPowerGeneration = 0;
        DailyData dailyData = batteryMainService.getDayData(2024,10,24);

        int now = LocalDateTime.now().getHour();
        Battery currentData = new Battery();
        for( Battery data : dailyDatas ){
            if(data.getHour() == now){
                currentData = data;
                break;
            }
        }

        model.addAttribute("monthData", monthData);
        model.addAttribute("dailyData", dailyData);
        model.addAttribute("currentData", currentData);
        return "html/main/main";
    }

//    @GetMapping("/header")
//    public String header() { return "header"; }
//
//    @GetMapping("/footer")
//    public String footer() { return "footer"; }

}

package com.example.demo.calendar.controller;

import com.example.demo.calendar.dto.ReportDTO;
import com.example.demo.calendar.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    @GetMapping("/report")
    public String showReportPage(Model model) {
        return "html/report/calendar";
    }

    @RestController
    public static class ReportApiController {

        @Autowired
        private ReportService reportService;

        // 특정 월의 리포트를 가져오는 API
        @GetMapping("/api/report")
        public List<ReportDTO> getReportsForMonth(@RequestParam(required = false) String yearMonth) {
            if (yearMonth == null || yearMonth.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                yearMonth = sdf.format(new Date());
            }
            return reportService.getReportsByMonth(yearMonth);
        }
    }
}

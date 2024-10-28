package com.example.demo.calendar.service;

import com.example.demo.calendar.dto.ReportDTO;
import com.example.demo.calendar.entity.Report;
import com.example.demo.calendar.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ReportDTO> getReportsByMonth(String yearMonth) {
        List<Report> reports = reportRepository.findByDateStartingWith(yearMonth);
        return reports.stream()
                .map(report -> new ReportDTO(
                        report.getDate(),
                        report.getTotTrans(),
                        report.getCharge(),
                        report.getDischarge()
                ))
                .collect(Collectors.toList());
    }
}


package com.example.demo.chart.repository;

import com.example.demo.chart.entity.MonthlyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyDataRepository extends JpaRepository<MonthlyData, Long> {
}



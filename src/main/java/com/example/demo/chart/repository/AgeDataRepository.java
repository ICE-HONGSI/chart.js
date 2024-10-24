package com.example.demo.chart.repository;

import com.example.demo.chart.entity.AgeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeDataRepository extends JpaRepository<AgeData, Long> {

}

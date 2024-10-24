package com.example.demo.weather.repository;

import com.example.demo.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findTopByOrderByIdDesc();
}

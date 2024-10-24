package com.example.demo.weather.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherDTO {

    private int id;
    private float temperature;
    private String sky;
    private String precipitation;
    private String time;
    private String gridX;
    private String gridY;
}

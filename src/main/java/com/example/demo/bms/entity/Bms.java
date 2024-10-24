package com.example.demo.bms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bms_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private double voltage;
    private double current;
    private double soc;
    private double temperature;
    private double averageVoltage;
    private double maxVoltage;
    private double minVoltage;
    private boolean alarm;
    private String state;
    private String detailInfo;
    private String tracingInfo;

}

package com.example.demo.main.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main")
@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 생성
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private int hour;

    @Column(nullable = false)
    @JsonProperty("chargeAmount")  // JSON에서 chargeAmount로 매핑
    private double charge_amount;

    @Column(nullable = false)
    @JsonProperty("dischargeAmount")
    private double discharge_amount;

    @Column(nullable = false)
    @JsonProperty("powerGeneration")
    private double power_generation;

    @Column(nullable = false)
    @JsonProperty("powerIncome")
    private double power_income;

    @Column()
    @JsonProperty("pvRcv")
    private int pvRcv;

    @Column(nullable = false)
    @JsonProperty("kepOut")
    private int kepOut;

    @Column()
    @JsonProperty("essRcv")
    private int essRcv;

    @Column()
    @JsonProperty("essOut")
    private int essOut;

    @Column()
    @JsonProperty("pcsStatus")
    private int pcsStatus;

    @Column()
    @JsonProperty("totalCap")
    private int totalCap;

    @Column()
    @JsonProperty("soc")
    private int soc;

    @Column()
    @JsonProperty("cap")
    private int cap;

    @Column()
    @JsonProperty("pcsTem")
    private float pcsTem;

    @Column()
    @JsonProperty("bmsTem")
    private float bmsTem;

    @Column()
    @JsonProperty("bmsHum")
    private int bmsHum;

    @Column()
    @JsonProperty("bmsStatus")
    private int bmsStatus;
}

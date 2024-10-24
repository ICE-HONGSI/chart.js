package com.example.demo.bms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "system_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bms;
    private String bmsIp;
    private String pcs;
    private String pcsIp;
    private String emu;
    private String emuIp;
    private String dpm;
    private String dpmIp;
}

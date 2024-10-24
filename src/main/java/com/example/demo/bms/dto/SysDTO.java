package com.example.demo.bms.dto;

import com.example.demo.bms.entity.Sys;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDTO {
    private String bms;
    private String bmsIp;
    private String pcs;
    private String pcsIp;
    private String emu;
    private String emuIp;
    private String dpm;
    private String dpmIp;

    public SysDTO(Sys system) {
        this.bms = system.getBms();
        this.bmsIp = system.getBmsIp();
        this.pcs = system.getPcs();
        this.pcsIp = system.getPcsIp();
        this.emu = system.getEmu();
        this.emuIp = system.getEmuIp();
        this.dpm = system.getDpm();
        this.dpmIp = system.getDpmIp();
    }

}
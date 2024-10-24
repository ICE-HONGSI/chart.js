package com.example.demo.bms.controller;

import com.example.demo.bms.dto.BmsDTO;
import com.example.demo.bms.dto.SysDTO;
import com.example.demo.bms.service.BmsService;
import com.example.demo.bms.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bms")
public class BmsController {

    @Autowired
    private BmsService bmsService;
    @Autowired
    private SysService sysService;



    @GetMapping("/rack")
    public String getBmsData(Model model) {
        List<BmsDTO> bmsData = bmsService.getBmsData();
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("bmsData", bmsData);
        return "html/bms_info_home"; // bms_info_home.html 템플릿을 반환
    }
    @GetMapping("/pcs")
    public String pcsInfo() {
        return "pcs_info"; // 타임리프 템플릿 (pcs_info.html)
    }

    @GetMapping("/system")
    public String systemInfo(Model model) {
        List<SysDTO> sysData = sysService.getSysData();
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("sysData", sysData);
        return "html/bms/system_info_page"; // 타임리프 템플릿 (system_info_page.html)
    }

    @GetMapping("/event")
    public String eventLog(Model model) {
        model.addAttribute("currentDate", LocalDate.now().toString());
        return "html/event_log_page"; // 타임리프 템플릿 (event_log_page.html)
    }
}

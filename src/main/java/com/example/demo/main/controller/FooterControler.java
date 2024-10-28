package com.example.demo.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.time.LocalDate;

@Controller
public class FooterControler {

    @GetMapping("/footerInfo")
    public String footerInfo(Model model){
        model.addAttribute("currentDate", LocalDate.now().toString());
        return "main";
    }
}

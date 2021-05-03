package com.dongverine.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/exp2/*")
public class ExternalController {
    @RequestMapping
    public String getAllEmployees(Model model) {
        log.info("call /exp/");
        return null;
    }
}

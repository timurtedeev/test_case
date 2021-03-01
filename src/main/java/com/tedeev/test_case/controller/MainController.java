package com.tedeev.test_case.controller;


import com.tedeev.test_case.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * todo Document type MainController
 */
@Controller
public class MainController {
    private final MainService eventService;

    @Autowired
    public MainController(MainService userService) {
        this.eventService = userService;
    }

    @GetMapping("/hello")
    public String main(Model model) {
        String message = "Select a task:";
        model.addAttribute("message", message);
        return "hello";
    }

    @GetMapping("/first_page")
    public String lastHour(Model model) {
        model.addAttribute("lastHourMap",eventService.getHourStatistic());
        return "first_page";
    }

    @GetMapping("/second_page")
    public String secondStatistic(Model model) {
        model.addAttribute("SecondStat",eventService.getSecondStatistic());
        return "second_page";
    }

    @GetMapping("/third_page")
    public String topForms(Model model) {
        model.addAttribute("topFormsMap",eventService.getTopForms());
        return "third_page";
    }
}
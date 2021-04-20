package com.example.CovidCaseTracker.controllers;


import com.example.CovidCaseTracker.models.Locations;
import com.example.CovidCaseTracker.services.CovidDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CovidDataServices covidDataServices;

    @GetMapping("/")
    public String index(Model model){
        List<Locations> all_locations = covidDataServices.getAll_location();
        int total_cases_reported = all_locations.stream().mapToInt(stat-> stat.getTotal_cases()).sum();
        int total_new_cases = all_locations.stream().mapToInt(stat-> stat.getDaily_case()).sum();
        model.addAttribute("all_location_value",all_locations);
        model.addAttribute("total_cases_reported",total_cases_reported);
        model.addAttribute("total_new_cases",total_new_cases);
        return "index";
    }

}

package com.controllers;

import com.dto.MealDTO;
import com.services.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller

public class HomePageController {
    private MealService mealService;

    public HomePageController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/")
    public String preaperHomePage(Model model) {




        model.addAttribute("mealDTO", mealService.findAllMeals());
        return "index";
    }


}

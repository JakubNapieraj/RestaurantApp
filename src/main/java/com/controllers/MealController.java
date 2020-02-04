package com.controllers;

import com.domain.Meal;
import com.dto.MealDTO;
import com.enums.Days;
import com.repositories.MealRepository;
import com.services.MealService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Slf4j
@Controller
public class MealController {

    private MealRepository mealRepository;
    private MealService mealService;


    public MealController(MealRepository mealRepository, MealService mealService) {
        this.mealRepository = mealRepository;
        this.mealService = mealService;
    }

    @GetMapping("/meals/add")
    public String preaperMealcreation(Model model) {
        model.addAttribute("mealDTO", new MealDTO());
        model.addAttribute("mewdays", Days.values());

        return "add-new-meal";
    }

    @PostMapping("/meals/add")
    public RedirectView addMeal(@ModelAttribute MealDTO mealDTO) {
        Meal newMeal = new Meal(mealDTO);
        newMeal.setActive(false);
        mealRepository.save(newMeal);
        log.info("Danie : " + mealDTO.getMealName() + " zostało dodane do menu.");
        return new RedirectView("/manager/logged");
    }

    @GetMapping("/meals/show-all")
    public String showAllMeals(Model model) {
        model.addAttribute("mealDTO", mealService.findAllMeals());

        return "all-meals";
    }

    @PostMapping(value = "/meals/delete/{id}")
    public String deleteMealByID(@PathVariable Long id, Model model) {

        mealRepository.deleteById(id);

        model.addAttribute("mealDTO", mealService.findAllMeals());
        return "all-meals";
    }

    @RequestMapping(value = "/meals/set-meal-active/{id}", method = RequestMethod.GET)
    public String setMealActiveByID(@PathVariable Long id, Model model) {

        Meal meal = mealRepository.getOne(id);
        if (meal.getActive()) {
            mealService.deactivateMeal(id);
            model.addAttribute("mealDTO", mealService.findAllMeals());
            return "all-meals";
        } else if (!meal.getActive()) {
            mealService.activateMeal(id);
        }

        model.addAttribute("mealDTO", mealService.findAllMeals());
        return "all-meals";
    }

}

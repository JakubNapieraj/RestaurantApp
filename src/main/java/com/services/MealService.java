package com.services;

import com.domain.Meal;
import com.dto.MealDTO;
import com.enums.Days;
import com.repositories.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    public List<MealDTO> findAllMeals() {
        return mealRepository.findAll().stream()
                .map(this::toMealDto)
                .collect(Collectors.toList());
    }

    private MealDTO toMealDto(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setMealName(meal.getMealName());
        mealDTO.setDescription(meal.getDescrpition());
        mealDTO.setPrice(meal.getPrice());
        mealDTO.setId(meal.getId());
        mealDTO.setDays(meal.getDays());
        mealDTO.setActive(meal.getActive());


        return mealDTO;
    }


    public void activateMeal(Long id){
        Meal meal = mealRepository.getOne(id);
        meal.setActive(true);
        mealRepository.save(meal);
    }

    public void deactivateMeal(Long id){
        Meal meal = mealRepository.getOne(id);
        meal.setActive(false);
        mealRepository.save(meal);
    }

    public Boolean checkIfMealIsActiveOrNot(Long id){
        Meal meal = mealRepository.getOne(id);
        return meal.getActive();
    }


}

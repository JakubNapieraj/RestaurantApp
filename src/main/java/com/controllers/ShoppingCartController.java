package com.controllers;


import com.domain.Meal;
import com.repositories.MealRepository;
import com.repositories.OrderRepository;
import com.session.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingCartController {

    private Cart cart;


    private MealRepository mealRepository;
    private OrderRepository orderRepository;

    public ShoppingCartController(Cart cart, MealRepository mealRepository, OrderRepository orderRepository) {
        this.cart = cart;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
    }
    @PostMapping("/cart/add/{mealId}")
    public String addMealToCart(@PathVariable Long mealId) {
        cart.addMealToCart(mealId);
        return "redirect:/cart/show";
    }

    @PostMapping("/cart/{mealId}")
    public String addToCart(@PathVariable Long mealId) {
        cart.addMealToCart(mealId);
        return "redirect:/";
    }


    @PostMapping("/cart/delete/{mealId}")
    public String deleteOneFromCart(@PathVariable Long mealId){

        cart.deleteMeal(Long.valueOf(mealId));
        return "redirect:/cart/show";
    }

    @GetMapping("/cart/quantity")
    public String showCartValue() {
        cart.showMealQuantity();

        return "redirect:/";

    }


}

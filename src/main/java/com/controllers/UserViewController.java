package com.controllers;

import com.domain.User;
import com.dto.OrderListDTO;
import com.dto.UserDTO;
import com.services.MealService;
import com.session.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserViewController {
    private UserController userController;
    private Cart cart;
    private MealService mealService;

    public UserViewController(UserController userController, Cart cart, MealService mealService) {
        this.userController = userController;
        this.cart = cart;
        this.mealService = mealService;
    }


    @GetMapping("/users")
    public String displayAllUsers(Model model) {
        model.addAttribute("users", userController.getAllUsers());
        return "display-all-users";
    }

    @GetMapping("/users/register")
    public String registerForm(Model model) {
        model.addAttribute("dto", new UserDTO());

        return "register";
    }

    @PostMapping("/users/register")
    public String registrationRedirect(){
        return "login";
    }


    @GetMapping("/users/login")
    public String loginForm(Model model){
        return "login";
    }

    @PostMapping("/users/login")
    public String logge(){
        return "redirect:/manager/logged";
    }

    @GetMapping("/manager/logged")
    public String loged(Model model){
        OrderListDTO orderListDTO = new OrderListDTO();
        model.addAttribute("mealDTO", mealService.findAllMeals());

        model.addAttribute("orderListDTO", cart.orderListDTOList());
        return "index-manager";
    }

}

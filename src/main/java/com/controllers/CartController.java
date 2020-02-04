package com.controllers;

import com.dto.OrderDTO;
import com.repositories.OrderMealQuantityRepository;
import com.repositories.OrderRepository;
import com.session.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class CartController {

    private Cart cart;
    private OrderRepository orderRepository;
    private OrderMealQuantityRepository quantityRepository;


    public CartController(Cart cart, OrderRepository orderRepository, OrderMealQuantityRepository quantityRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.quantityRepository = quantityRepository;
    }

    @GetMapping("/cart/show")
    public String  showOrder( Model model) {



        model.addAttribute("mealQuantityDTO", cart.orderPositions());

        return "cart-site";

    }

    @PostMapping("/cart/show")
    public String processOrder(){

        return "redirect:/cart/finalize";
    }

}

package com.controllers;

import com.dto.OrderDTO;
import com.repositories.OrderMealQuantityRepository;
import com.repositories.OrderRepository;
import com.services.EmailSender;
import com.session.Cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Controller
@Slf4j
public class OrderController {

    private final TemplateEngine templateEngine;
    private final EmailSender emailSender;
    private Cart cart;
    private OrderRepository orderRepository;
    private OrderMealQuantityRepository quantityRepository;


    @Autowired
    public OrderController(TemplateEngine templateEngine, EmailSender emailSender, Cart cart, OrderRepository orderRepository, OrderMealQuantityRepository quantityRepository) {
        this.templateEngine = templateEngine;
        this.emailSender = emailSender;
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.quantityRepository = quantityRepository;
    }

    @GetMapping("/cart/finalize")
    public String sumbitOrder(Model model) {

        OrderDTO orderDTO = new OrderDTO();

        model.addAttribute("mealQuantityDTO", cart.orderPositions());
        model.addAttribute("orderDTO", orderDTO);

        return "finalize-order";

    }

    @PostMapping("/cart/finalize")
    public String sendEmail(OrderDTO orderDTO, Model model) throws MessagingException {

        cart.addOrderToHibernate(orderDTO);

        Context context = new Context();
        context.setVariable("header", "Nowe zamówienie ");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail(orderDTO.getEmail(), orderDTO.getOrderDate().toString(), body);
        cart.clearCart();

        return "redirect:/";
    }


}

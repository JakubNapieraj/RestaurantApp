package com.controllers;

import com.dto.OrderListDTO;
import com.session.Cart;
import com.sun.mail.imap.protocol.MODSEQ;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderLIstController {

    private Cart cart;

    public OrderLIstController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("/order-list/show")
    public String showOrderList(Model model){
        OrderListDTO orderListDTO = new OrderListDTO();

        model.addAttribute("orderListDTO", cart.orderListDTOList());
        return "index-manager-2";
    }



    @GetMapping("/order-list-between-two-dates/show")
    public String showOrderListBetweenTwoDat(Model model){

        OrderListDTO orderListDTO = new OrderListDTO();

        model.addAttribute("orderListDTO", cart.orderListDTOListTwoDat());
        return "index-manager-2";
    }


}

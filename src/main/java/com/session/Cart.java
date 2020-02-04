package com.session;

import com.domain.Meal;
import com.domain.Order;
import com.domain.OrderMealQuantity;
import com.dto.MealQuantityDTO;
import com.dto.OrderDTO;
import com.dto.OrderListDTO;
import com.repositories.MealRepository;
import com.repositories.OrderMealQuantityRepository;
import com.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class Cart {

    private OrderMealQuantityRepository orderMealQuantityRepository;
    private EntityManager entityManager;
    private OrderRepository orderRepository;
    private Map<Meal, Integer> session = new HashMap<>();
    private MealRepository mealRepository;


    @Autowired
    public Cart(OrderMealQuantityRepository orderMealQuantityRepository, EntityManager entityManager, OrderRepository orderRepository, MealRepository mealRepository) {
        this.orderMealQuantityRepository = orderMealQuantityRepository;
        this.entityManager = entityManager;
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
    }


    public void addMealToCart(Long mealId) {

        Meal meal = mealRepository.findById(mealId).orElseThrow(RuntimeException::new);


        if (session.containsKey(meal)) {
            session.put(meal, session.get(meal) + 1);
        } else
            session.put(meal, 1);
    }

    public void deleteMeal(Long mealId) {

        Meal meal = mealRepository.findById(mealId).orElseThrow(RuntimeException::new);

        if (session.isEmpty()) {
            log.info("Twój koszyk jest pusty.");
        } else if (session.containsKey(meal)) {

            session.replace(meal, session.get(meal)-1);
            if (session.get(meal)== 0){
                session.remove(meal);
            }
        }
    }


    public void clearCart(){
        session.clear();
    }

    @Transactional
    public Order addOrderToHibernate(OrderDTO dto) {

        dto.setOrderDate(LocalDate.now());
        Order order = new Order(dto);
        orderRepository.save(order);

        for (Map.Entry<Meal, Integer> entry : session.entrySet()) {
            Meal m = entry.getKey();
            Integer i = entry.getValue();
            OrderMealQuantity omq = new OrderMealQuantity(order, m, i);
            orderMealQuantityRepository.save(omq);
        }


        entityManager.refresh(order);

        return order;

    }

    public String showMealQuantity() {
        return session.entrySet().stream()
                .map(e -> e.getKey().getMealName() + " " + e.getValue() + " "+ e.getKey().getPrice()*e.getValue())
                .collect(Collectors.joining(","));
    }

    public List<MealQuantityDTO> orderPositions(){

        return this.session.entrySet().stream()
                .map(e -> new MealQuantityDTO(e.getKey().getId(), e.getKey().getMealName(), e.getKey().getPrice(), e.getValue()))
                .collect(Collectors.toList());
        
    }


    public List<OrderListDTO> orderListDTOList(){

        return orderMealQuantityRepository.findAll().stream().map(e -> new OrderListDTO(e.getOrder().getId(), e.getMeal().getMealName(), e.getMeal().getPrice(), e.getQuantity(), e.getOrder().getOrderDate(), e.getOrder().getFirstName(), e.getOrder().getLastName(), e.getOrder().getEmail(), e.getOrder().getCity(), e.getOrder().getStreetNumber(), e.getOrder().getPostCode()))
                .collect(Collectors.toList());
    }

    public List<OrderListDTO> orderListDTOListOneDat(){

        LocalDate dateTime = LocalDate.now();

    return orderMealQuantityRepository.findAllByOrder_OrderDate(dateTime).stream().map(e -> new OrderListDTO(e.getOrder().getId(), e.getMeal().getMealName(), e.getMeal().getPrice(), e.getQuantity(), e.getOrder().getOrderDate(), e.getOrder().getFirstName(), e.getOrder().getLastName(), e.getOrder().getEmail(), e.getOrder().getCity(), e.getOrder().getStreetNumber(), e.getOrder().getPostCode()))
            .collect(Collectors.toList());
    }
    public List<OrderListDTO> orderListDTOListTwoDat(){

        LocalDate date1 = LocalDate.of(2019, 12, 21);
        LocalDate date2 = LocalDate.of(2019, 12, 23);

        return orderMealQuantityRepository.findAllByOrder_OrderDateBetween(date1, date2).stream().map(e -> new OrderListDTO(e.getOrder().getId(), e.getMeal().getMealName(), e.getMeal().getPrice(), e.getQuantity(), e.getOrder().getOrderDate(), e.getOrder().getFirstName(), e.getOrder().getLastName(), e.getOrder().getEmail(), e.getOrder().getCity(), e.getOrder().getStreetNumber(), e.getOrder().getPostCode()))
                .collect(Collectors.toList());
    }

    public Map<Meal, Integer> getSession() {
        return session;
    }
}

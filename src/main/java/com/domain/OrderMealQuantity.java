package com.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderMealQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Meal meal;

    @Column
    private int quantity;

    public OrderMealQuantity(Order order, Meal meal, int quantity) {
        this.order = order;
        this.meal = meal;
        this.quantity = quantity;
    }


}

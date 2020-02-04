package com.domain;

import com.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    public Order(OrderDTO orderDTO) {
        this.orderDate = orderDTO.getOrderDate();
        this.firstName = orderDTO.getFirstName();
        this.lastName = orderDTO.getLastName();
        this.email = orderDTO.getEmail();
        this.city = orderDTO.getCity();
        this.streetNumber = orderDTO.getStreetNumber();
        this.postCode = orderDTO.getPostCode();

    }

    public Order() {
    }

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "firtName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "postCode")
    private String postCode;


    @OneToMany(mappedBy = "order")
    private Set<OrderMealQuantity> entries = new HashSet<>();

    @ManyToOne
    private User user;









}

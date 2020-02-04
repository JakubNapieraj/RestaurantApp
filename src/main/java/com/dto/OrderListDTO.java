package com.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderListDTO {

    private Long id;
    private String mealName;
    private double price;
    private int quantity;
    private LocalDate orderDate;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String streetNumber;
    private String postCode;

    public OrderListDTO() {

    }

    public OrderListDTO(Long id, String mealName, double price, int quantity, LocalDate orderDate, String firstName, String lastName, String email, String city, String streetNumber, String postCode) {
        this.id = id;
        this.mealName = mealName;
        this.price = price;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

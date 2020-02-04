package com.dto;

public class MealQuantityDTO {

    public Long getMealId() {
        return mealId;
    }

    private Long mealId;
    private String mealName;
    private double price;
    private int quantity;

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

    public MealQuantityDTO() {
    }

    public MealQuantityDTO(Long mealId,String mealName, double price, int quantity) {
       this.mealId = mealId;
        this.mealName = mealName;
        this.price = price;
        this.quantity = quantity;
    }
}

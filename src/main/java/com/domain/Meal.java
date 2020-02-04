package com.domain;

import com.dto.MealDTO;
import com.enums.Days;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "meals")
@ToString
@EqualsAndHashCode
public class Meal  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mealName")
    private String mealName;

    @Column(name = "description")
    private String descrpition;

    @Column(name = "price")
    private Double price;

    @Column(name = "days")
    @Enumerated(EnumType.STRING)
    private Days days;

    @Column(name = "active", nullable = false)
    private Boolean active ;

    public Meal() {
    }

    public Meal(MealDTO  mealDTO) {
        this.mealName = mealDTO.getMealName();
        this.descrpition = mealDTO.getDescription();
        this.price = mealDTO.getPrice();
        this.days = mealDTO.getDays();
        this.active = mealDTO.getActive();
    }
}

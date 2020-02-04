package com.repositories;


import com.domain.OrderMealQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface OrderMealQuantityRepository extends JpaRepository<OrderMealQuantity, Long> {

    List<OrderMealQuantity> findAllByOrder_OrderDate(LocalDate orderDate);

    List<OrderMealQuantity> findAllByOrder_OrderDateBetween(
            LocalDate orderTime1,
            LocalDate orderTime2);



}

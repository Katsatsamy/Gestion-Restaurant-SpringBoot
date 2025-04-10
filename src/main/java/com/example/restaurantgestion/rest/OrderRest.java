package com.example.restaurantgestion.rest;

import com.example.restaurantgestion.entity.DishOrderRest;
import com.example.restaurantgestion.entity.OrderStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderRest {
    private String id;
    private List<DishOrderRest> dishOrderRests;
    private OrderStatus status;
}

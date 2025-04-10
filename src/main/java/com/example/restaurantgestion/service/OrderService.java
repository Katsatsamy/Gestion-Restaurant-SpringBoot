package com.example.restaurantgestion.service;

import com.example.restaurantgestion.entity.Dish;
import com.example.restaurantgestion.entity.DishOrder;
import com.example.restaurantgestion.entity.DishOrderRest;
import com.example.restaurantgestion.entity.Order;
import com.example.restaurantgestion.repository.DishOrderCudOperation;
import com.example.restaurantgestion.repository.OrderCrudOperation;
import com.example.restaurantgestion.rest.OrderRest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderCrudOperation orderCrudOperation;
    private DishOrderCudOperation dishOrderCudOperation;

    public ResponseEntity getOrderById(String orderId) {
        Order order = orderCrudOperation.findById(orderId);
        if(order == null) {
            return ResponseEntity.status(404).body("Order id = " + orderId + " not found");
        }
        List<DishOrderRest> dishOrderRests = new ArrayList<>();
        List<DishOrder> dishOrders = order.getDishOrders();
        for (DishOrder dishOrder : dishOrders) {
            DishOrderRest dishOrderRest = new DishOrderRest(
                    dishOrder.getId(),
                    dishOrder.getDish().getName(),
                    dishOrder.getDish().getUnitPrice(),
                    dishOrder.getQuantity(),
                    dishOrder.getActualStatus().getStatus()
            );
            dishOrderRests.add(dishOrderRest);
        }
        OrderRest orderRest = new OrderRest(
                order.getId(),
                dishOrderRests,
                order.getActualStatus());
        return ResponseEntity.ok(orderRest);
    }

    public ResponseEntity addDish(String orderId, List<DishOrderRest> dishOrderRests) {
        return orderCrudOperation.addDish(orderId, dishOrderRests);
    }

    public ResponseEntity changeStatus(String orderId, String dishId){
        return ResponseEntity.ok(dishOrderCudOperation.changeStatus(orderId, dishId));
    }

}

package com.example.restaurantgestion.controller;

import com.example.restaurantgestion.entity.DishOrderRest;
import com.example.restaurantgestion.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {
    private OrderService orderService;

    @GetMapping("/orders/{reference}")
    public ResponseEntity getOrderById(@PathVariable String reference) {
        return ResponseEntity.ok(orderService.getOrderById(reference));
    }

    @PutMapping("/orders/{reference}/dishes")
    public ResponseEntity addDish(@PathVariable String reference, @RequestBody List<DishOrderRest> dishOrderRests) {
        return ResponseEntity.ok(orderService.addDish(reference, dishOrderRests));
    }

    @PutMapping("/orders/{reference}/dishes/{dishId}")
    public ResponseEntity changeStatus(@PathVariable String reference, @PathVariable String dishId) {
        return ResponseEntity.ok(orderService.changeStatus(reference, dishId));
    }
}

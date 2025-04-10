package com.example.restaurantgestion.service;

import com.example.restaurantgestion.repository.BestSalesDishCrudOperation;
import com.example.restaurantgestion.rest.BestSalesDish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BestSalesDishesService {
    private final BestSalesDishCrudOperation bestSalesDishCrudOperation;

    public List<BestSalesDish> getAllBestSalesDishes(LocalDateTime startDate, LocalDateTime endDate, Double X) {
        if(X == null || X <= 0) {
            throw new IllegalArgumentException("X must be a positive number");
        }

        return bestSalesDishCrudOperation.getAllByDate(startDate, endDate, X);
    }
}

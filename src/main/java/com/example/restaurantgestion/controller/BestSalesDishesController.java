package com.example.restaurantgestion.controller;

import com.example.restaurantgestion.rest.BestSalesDish;
import com.example.restaurantgestion.service.BestSalesDishesService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class BestSalesDishesController {
    private BestSalesDishesService bestSalesDishesService;

    @GetMapping("/bestSales/{X}")
    public List<BestSalesDish> getAllBestSalesDishes(
            @PathVariable Double X,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return bestSalesDishesService.getAllBestSalesDishes(startDate, endDate, X);
    }
}

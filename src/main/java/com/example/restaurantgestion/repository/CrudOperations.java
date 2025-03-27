package com.example.restaurantgestion.repository;

import java.util.List;

public interface CrudOperations <E> {
    List<E> getAll(int page, int size);
    E findById(String id);
    List<E> findByName(String name);
    List<E> saveAll(List<E> list);
}

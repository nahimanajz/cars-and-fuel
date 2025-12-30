package com.codeland.backend.repository;

import com.codeland.backend.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepository {
    Car save(Car car);

    Optional<Car> findById(String id);

    List<Car> findAll();
}

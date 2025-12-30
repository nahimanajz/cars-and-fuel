package com.codeland.backend.repository;

import com.codeland.backend.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCarRepository implements CarRepository {
    private final Map<String, Car> storage = new ConcurrentHashMap<>();

    @Override
    public Car save(Car car) {
        storage.put(car.getId(), car);
        return car;
    }

    @Override
    public Optional<Car> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(storage.values());
    }
}

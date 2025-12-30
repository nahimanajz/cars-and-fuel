package com.codeland.backend.controller;

import com.codeland.backend.dto.AddFuelRequest;
import com.codeland.backend.dto.CarStats;
import com.codeland.backend.dto.CreateCarRequest;
import com.codeland.backend.model.Car;
import com.codeland.backend.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CreateCarRequest request) {
        Car car = carService.createCar(request.getBrand(), request.getModel(), request.getYear());
        return ResponseEntity.ok(car);
    }

    @GetMapping
    public ResponseEntity<List<Car>> listCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping("/{id}/fuel")
    public ResponseEntity<Void> addFuel(@PathVariable("id") String id, @RequestBody AddFuelRequest request) {
        carService.addFuel(id, request.getLiters(), request.getPrice(), request.getOdometer());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/fuel/stats")
    public ResponseEntity<CarStats> getStats(@PathVariable("id") String id) {
        return ResponseEntity.ok(carService.getStats(id));
    }
}

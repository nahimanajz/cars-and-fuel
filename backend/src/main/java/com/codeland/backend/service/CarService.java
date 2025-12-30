package com.codeland.backend.service;

import com.codeland.backend.dto.CarStats;
import com.codeland.backend.model.Car;
import com.codeland.backend.model.FuelEntry;
import com.codeland.backend.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(String brand, String model, int year) {
        Car car = new Car(brand, model, year);
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addFuel(String carId, double liters, double price, double odometer) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NoSuchElementException("Car not found with id: " + carId));

        FuelEntry entry = new FuelEntry(liters, price, odometer, LocalDateTime.now());
        car.getFuelEntries().add(entry);
        return carRepository.save(car);
    }

    public CarStats getStats(String carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NoSuchElementException("Car not found with id: " + carId));

        List<FuelEntry> entries = car.getFuelEntries();
        if (entries.isEmpty()) {
            return new CarStats(0, 0, 0);
        }

        double totalFuel = entries.stream().mapToDouble(FuelEntry::getLiters).sum();
        double totalCost = entries.stream().mapToDouble(FuelEntry::getPrice).sum();

        double averageConsumption = 0.0;

        if (entries.size() > 1) {
            entries.sort(Comparator.comparingDouble(FuelEntry::getOdometer));
            double minOdometer = entries.get(0).getOdometer();
            double maxOdometer = entries.get(entries.size() - 1).getOdometer();
            double distance = maxOdometer - minOdometer;

            if (distance > 0) {
                averageConsumption = (totalFuel / distance) * 100;
                averageConsumption = Math.round(averageConsumption * 10.0) / 10.0;
            }
        }

        return new CarStats(totalFuel, totalCost, averageConsumption);

    }
}

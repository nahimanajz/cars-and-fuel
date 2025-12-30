package com.codeland.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelEntry {
    private double liters;
    private double price;
    private double odometer;
    private LocalDateTime timestamp;
}

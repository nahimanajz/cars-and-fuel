package com.codeland.backend.dto;

import lombok.Data;

@Data
public class AddFuelRequest {
    private double liters;
    private double price;
    private double odometer;
}

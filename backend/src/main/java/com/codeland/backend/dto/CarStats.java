package com.codeland.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarStats {
    private double totalFuel;
    private double totalCost;
    private double averageConsumption;
}

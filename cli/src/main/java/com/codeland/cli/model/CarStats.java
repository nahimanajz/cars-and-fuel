package com.codeland.cli.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarStats {
    private double totalFuel;
    private double totalCost;
    private double averageConsumption;

    public double getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(double totalFuel) {
        this.totalFuel = totalFuel;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    @Override
    public String toString() {
        return String.format("Total fuel: %.2f L\nTotal cost: %.2f\nAverage consumption: %.1f L/100km", totalFuel,
                totalCost, averageConsumption);
    }
}

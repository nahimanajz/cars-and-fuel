package com.codeland.cli;

import com.codeland.cli.client.ApiClient;
import com.codeland.cli.model.Car;
import com.codeland.cli.model.CarStats;

import java.util.HashMap;
import java.util.Map;

public class CarCliApplication {

    private static final String DEFAULT_BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String command = args[0];
        ApiClient apiClient = new ApiClient(DEFAULT_BASE_URL);

        try {
            switch (command) {
                case "create-car":
                    handleCreateCar(args, apiClient);
                    break;
                case "add-fuel":
                    handleAddFuel(args, apiClient);
                    break;
                case "fuel-stats":
                    handleFuelStats(args, apiClient);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    printUsage();
            }
        } catch (Exception e) {
            System.err.println("Error executing command: " + e.getMessage());
            // e.printStackTrace(); // Optional: invalid args usually don't need stacktrace
        }
    }

    private static void handleCreateCar(String[] args, ApiClient client) throws Exception {
        Map<String, String> params = parseArgs(args);
        String brand = params.get("brand");
        String model = params.get("model");
        String yearStr = params.get("year");

        if (brand == null || model == null || yearStr == null) {
            System.out.println("Usage: create-car --brand <brand> --model <model> --year <year>");
            return;
        }

        Car car = client.createCar(brand, model, Integer.parseInt(yearStr));
        System.out.println("Car created: " + car.getId() + car.toString()); 
    }

    private static void handleAddFuel(String[] args, ApiClient client) throws Exception {
        Map<String, String> params = parseArgs(args);
        String carId = params.get("carId");
        String litersStr = params.get("liters");
        String priceStr = params.get("price");
        String odometerStr = params.get("odometer");

        if (carId == null || litersStr == null || priceStr == null || odometerStr == null) {
            System.out.println("Usage: add-fuel --carId <id> --liters <liters> --price <price> --odometer <odometer>");
            return;
        }

        client.addFuel(carId, Double.parseDouble(litersStr), Double.parseDouble(priceStr),
                Double.parseDouble(odometerStr));
        System.out.println("Fuel added successfully.");
    }

    private static void handleFuelStats(String[] args, ApiClient client) throws Exception {
        Map<String, String> params = parseArgs(args);
        String carId = params.get("carId");

        if (carId == null) {
            System.out.println("Usage: fuel-stats --carId <id>");
            return;
        }

        CarStats stats = client.getStats(carId);
        System.out.println(stats);
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String key = args[i].substring(2);
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    params.put(key, args[i + 1]);
                    i++;
                } else {
                    // Start flag without value? Or maybe boolean flag?
                    // Assuming all args need values for this assignment
                }
            }
        }
        return params;
    }

    private static void printUsage() {
        System.out.println("Available commands:");
        System.out.println("  create-car --brand <brand> --model <model> --year <year>");
        System.out.println("  add-fuel --carId <id> --liters <liters> --price <price> --odometer <odometer>");
        System.out.println("  fuel-stats --carId <id>");
    }
}

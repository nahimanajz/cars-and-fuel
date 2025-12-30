# Car Management System

A simple Car Management System with a Spring Boot Backend and a Java CLI Client.
This repository implements a repository pattern with in-memory storage, adhering to SOLID principles.

## Prerequisites

- Java 17+
- Maven 3.6+

## Project Structure

- `backend`: Spring Boot application handling logic and data (port 8080).
- `cli`: Java command-line interface module.

## 🚀 Getting Started

### 1. Build the Project
In the root directory, run:
```bash
mvn clean install -DskipTests
```

### 2. Start the Backend SERVER
```bash
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
```

### 3. API Documentation (Swagger UI)
Once the backend is running, access the interactive API documentation at:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 🛠 Usage via CLI

The CLI is a separate standalone application that interacts with the backend.

### 1. Create a Car
Registers a new car in the system.
```bash
java -jar cli/target/cli-0.0.1-SNAPSHOT.jar create-car --brand Toyota --model Corolla --year 2018
```
**Output**: `Car created: <UUID>`

### 2. Add Fuel Entry
Adds a record of a refueling event.
```bash
java -jar cli/target/cli-0.0.1-SNAPSHOT.jar add-fuel --carId <UUID> --liters 40 --price 52.5 --odometer 45000
```
**Parameters**:
- `--carId`: The UUID returned from `create-car`.
- `--liters`: Amount of fuel added.
- `--price`: Total price of the fuel.
- `--odometer`: Current odometer reading.

### 3. Get Fuel Statistics
Retrieves total fuel, cost, and average consumption.
```bash
java -jar cli/target/cli-0.0.1-SNAPSHOT.jar fuel-stats --carId <UUID>
```

### Fuel Calculation Logic
The system calculates average consumption using the following formula:
\[
\text{Consumption} = \left( \frac{\text{Sum of All Liters}}{\text{Max Odometer} - \text{Min Odometer}} \right) \times 100
\]
*Rounded to 1 decimal place.*

**Note**: You must add at least **two** fuel entries with different odometer readings to calculate distance. If `Distance == 0` (e.g., single entry), consumption returns `0.0`.

**Example Scenario**:
To achieve a result of `6.4 L/100km` with `120L` total fuel:
1. Create Car.
2. Add Entry 1: Start point (e.g., 10,000 km).
3. Add Entry 2: End point (e.g., 11,875 km, with cumulative liters summing to 120L).
   - Distance = 1875 km.
   - Calculation: `(120 / 1875) * 100 = 6.4`.

---

## 🔌 API Endpoints

### REST API
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/cars` | Create a new car |
| `GET` | `/api/cars` | List all cars |
| `POST` | `/api/cars/{id}/fuel` | Add a fuel entry |
| `GET` | `/api/cars/{id}/fuel/stats` | Get statistics for a car |

### Manual Servlet (Java Servlet API)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/servlet/fuel-stats?carId={id}` | Get statistics via raw Servlet |

## Verification & Testing
To manually verify the application flow:
1. Start the backend.
2. Run the `create-car` command using the CLI.
3. Run the `add-fuel` command at least twice with increasing odometer values.
4. Run `fuel-stats` to see the calculation.
5. Visit `http://localhost:8080/servlet/fuel-stats?carId=<UUID>` in your browser to verify the Servlet.

## Technical Details
- **Pattern**: Repository Pattern (In-Memory Implementation)
- **Design Principles**: SOLID, DRY
- **Storage**: `ConcurrentHashMap` (Data is lost on restart)

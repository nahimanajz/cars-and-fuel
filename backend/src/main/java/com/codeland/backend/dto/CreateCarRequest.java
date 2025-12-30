package com.codeland.backend.dto;

import lombok.Data;

@Data
public class CreateCarRequest {
    private String brand;
    private String model;
    private int year;
}

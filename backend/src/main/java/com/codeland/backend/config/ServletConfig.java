package com.codeland.backend.config;

import com.codeland.backend.service.CarService;
import com.codeland.backend.servlet.FuelStatsServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet(CarService carService,
            ObjectMapper objectMapper) {
        FuelStatsServlet servlet = new FuelStatsServlet(carService, objectMapper);
        ServletRegistrationBean<FuelStatsServlet> bean = new ServletRegistrationBean<>(servlet, "/servlet/fuel-stats");
        return bean;
    }
}

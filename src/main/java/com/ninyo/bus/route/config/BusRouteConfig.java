package com.ninyo.bus.route.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ninyo.bus.route.dao.BusRouteDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Configuration
public class BusRouteConfig {

    @Bean
    public CommandLineRunner commandLineRunner(BusRouteDao busRouteDao) {
        return args -> {
            if (args.length < 1 || StringUtils.isEmpty(args[0])) {
                log.error("Missing path to bus route data file as first argument");
                System.exit(-1);
            }
            try {
                busRouteDao.initializeBusRoutes(args[0]);
            } catch (IOException e) {
                log.error("Could not initialize bus routes: " + e.getMessage(), e);
                System.exit(-1);
            }
        };
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return builder;
    }
}

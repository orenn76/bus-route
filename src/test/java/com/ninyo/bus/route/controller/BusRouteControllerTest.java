package com.ninyo.bus.route.controller;

import com.ninyo.bus.route.service.BusRouteService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BusRouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusRouteService service;

    @MockBean
    private CommandLineRunner commandLineRunner;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("routesFilePath", "");
    }

    @Test
    public void shouldReturnBusRouteResponseWithDirectBusRouteTrue() throws Exception {
        when(service.hasDirectBusRoute(anyInt(), anyInt())).thenReturn(true);

        this.mockMvc.perform(get("/direct").param("dep_sid", "3").param("arr_sid", "6"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.dep_sid").value("3"))
                .andExpect(jsonPath("$.arr_sid").value("6"))
                .andExpect(jsonPath("$.direct_bus_route").value("true"));
    }

    @Test
    public void shouldReturnBusRouteResponseWithDirectBusRouteFalse() throws Exception {
        when(service.hasDirectBusRoute(anyInt(), anyInt())).thenReturn(false);

        this.mockMvc.perform(get("/direct").param("dep_sid", "5").param("arr_sid", "7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.dep_sid").value("5"))
                .andExpect(jsonPath("$.arr_sid").value("7"))
                .andExpect(jsonPath("$.direct_bus_route").value("false"));
    }

    @Test
    public void shouldReturnBadRequestWhenDepartureStationIdIsMissing() throws Exception {
        this.mockMvc.perform(get("/direct").param("arr_sid", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenArrivalStationIdIsMissing() throws Exception {
        this.mockMvc.perform(get("/direct").param("dep_sid", "5"))
                .andExpect(status().isBadRequest());
    }
}
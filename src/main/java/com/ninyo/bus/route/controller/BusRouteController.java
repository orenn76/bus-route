package com.ninyo.bus.route.controller;

import com.ninyo.bus.route.model.BusRouteResponse;
import com.ninyo.bus.route.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BusRouteResponse directBusRoute(
            @RequestParam(value = "dep_sid", required = true) Integer depSid,
            @RequestParam(value = "arr_sid", required = true) Integer arrSid) {
        return BusRouteResponse.builder()
                .depSid(depSid)
                .arrSid(arrSid)
                .directBusRoute(busRouteService.hasDirectBusRoute(depSid, arrSid))
                .build();
    }
}

package com.ninyo.bus.route.model;

import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class BusRoute {

    final Integer id;
    final Integer stationIndex;
}
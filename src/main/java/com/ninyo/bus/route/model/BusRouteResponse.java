package com.ninyo.bus.route.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@JsonPropertyOrder({"depSid", "arrSid", "directBusRoute"})
@Builder(toBuilder = true)
@Value
@JsonDeserialize(builder = BusRouteResponse.BusRouteResponseBuilder.class)
public class BusRouteResponse implements Serializable {

    private static final long serialVersionUID = -3830773528132366911L;

    private Integer depSid;
    private Integer arrSid;
    private Boolean directBusRoute;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class BusRouteResponseBuilder {
    }
}


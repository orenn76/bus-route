package com.ninyo.bus.route.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder(toBuilder = true)
@Value
@JsonDeserialize(builder = ErrorResponse.ErrorResponseBuilder.class)
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 8778837331429823333L;

    private String message;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ErrorResponseBuilder {
    }
}

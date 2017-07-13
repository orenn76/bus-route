package com.ninyo.bus.route.controller;

import com.ninyo.bus.route.model.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class RestResponseEntityExceptionHandlerTest {

    private RestResponseEntityExceptionHandler exceptionHandler;

    private WebRequest request;

    private MockHttpServletRequest servletRequest;

    private MockHttpServletResponse servletResponse;

    @Before
    public void setup() {
        this.exceptionHandler = new RestResponseEntityExceptionHandler();
        this.servletRequest = new MockHttpServletRequest("GET", "/");
        this.servletResponse = new MockHttpServletResponse();
        this.request = new ServletWebRequest(this.servletRequest, this.servletResponse);
    }

    @Test
    public void anyExceptionClassCast() {
        Exception ex = new ClassCastException("classCastException");
        ResponseEntity<ErrorMessage> responseEntity = this.exceptionHandler.handleAnyException(ex, this.servletRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void accessDeniedException() {
        Exception ex = new AccessDeniedException("accessDeniedException");
        ResponseEntity<ErrorMessage> responseEntity = this.exceptionHandler.handleAccessDenied(ex, this.servletRequest);
        assertEquals(HttpStatus.FORBIDDEN.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void unsupportedOperationException() {
        Exception ex = new UnsupportedOperationException("unsupportedOperationException");
        ResponseEntity<ErrorMessage> responseEntity = this.exceptionHandler.handleUnsupportedOperation(ex, this.servletRequest);
        assertEquals(HttpStatus.NOT_IMPLEMENTED.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void constraintViolationException() {
        Exception ex = Mockito.mock(ConstraintViolationException.class);
        ResponseEntity<ErrorMessage> responseEntity = this.exceptionHandler.handleConstraintViolation(ex, this.servletRequest);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void handleNoSuchElementException() {
        Exception ex = Mockito.mock(NoSuchElementException.class);
        ResponseEntity<ErrorMessage> responseEntity = this.exceptionHandler.handleNoSuchElementException(ex, this.servletRequest);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCode().value());
    }

    @Test
    public void handleExceptionNoSuchRequestHandlingMethod() {
        Exception ex = Mockito.mock(NoSuchRequestHandlingMethodException.class);
        ResponseEntity<Object> responseEntity = this.exceptionHandler.handleException(ex, this.request);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCode().value());
    }
}
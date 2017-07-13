package com.ninyo.bus.route.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteDaoImplTest {

    private static final int MIN_LENGTH = 3;
    private static final String SPACE = " ";

    @InjectMocks
    private BusRouteDaoImpl dao;

    @Before
    public void before() throws IOException {
        dao.initializeBusRoutes("src/test/resources/data/example");
    }

    @Test
    public void shouldReturnHasDirectBusRouteTrue() {
        assertTrue(dao.hasDirectBusRoute(0, 1));
        assertTrue(dao.hasDirectBusRoute(0, 2));
        assertTrue(dao.hasDirectBusRoute(0, 3));
        assertTrue(dao.hasDirectBusRoute(0, 4));
        assertTrue(dao.hasDirectBusRoute(1, 2));
        assertTrue(dao.hasDirectBusRoute(1, 3));
        assertTrue(dao.hasDirectBusRoute(1, 4));
        assertTrue(dao.hasDirectBusRoute(2, 3));
        assertTrue(dao.hasDirectBusRoute(2, 4));
        assertTrue(dao.hasDirectBusRoute(3, 4));
        assertTrue(dao.hasDirectBusRoute(3, 1));
        assertTrue(dao.hasDirectBusRoute(3, 6));
        assertTrue(dao.hasDirectBusRoute(3, 5));
        assertTrue(dao.hasDirectBusRoute(1, 6));
        assertTrue(dao.hasDirectBusRoute(1, 5));
        assertTrue(dao.hasDirectBusRoute(6, 5));
        assertTrue(dao.hasDirectBusRoute(0, 6));
        assertTrue(dao.hasDirectBusRoute(0, 4));
        assertTrue(dao.hasDirectBusRoute(6, 4));
    }

    @Test
    public void shouldReturnHasDirectBusRouteFalse() {
        assertFalse(dao.hasDirectBusRoute(1, 0));
        assertFalse(dao.hasDirectBusRoute(2, 0));
        assertFalse(dao.hasDirectBusRoute(3, 0));
        assertFalse(dao.hasDirectBusRoute(4, 0));
        assertFalse(dao.hasDirectBusRoute(2, 1));
        assertFalse(dao.hasDirectBusRoute(4, 1));
        assertFalse(dao.hasDirectBusRoute(3, 2));
        assertFalse(dao.hasDirectBusRoute(4, 2));
        assertFalse(dao.hasDirectBusRoute(4, 3));
        assertFalse(dao.hasDirectBusRoute(6, 3));
        assertFalse(dao.hasDirectBusRoute(5, 3));
        assertFalse(dao.hasDirectBusRoute(6, 1));
        assertFalse(dao.hasDirectBusRoute(5, 1));
        assertFalse(dao.hasDirectBusRoute(5, 6));
        assertFalse(dao.hasDirectBusRoute(6, 0));
        assertFalse(dao.hasDirectBusRoute(4, 0));
        assertFalse(dao.hasDirectBusRoute(4, 6));
    }
}

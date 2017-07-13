package com.ninyo.bus.route.service;

import com.ninyo.bus.route.dao.BusRouteDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusRouteServiceTest {

    @Mock
    private BusRouteDao busRouteDao;

    @InjectMocks
    private BusRouteServiceImpl service;

    @Test
    public void shouldReturnTrue() {
        //Given
        when(busRouteDao.hasDirectBusRoute(anyInt(), anyInt())).thenReturn(true);

        //When
        boolean hasDirectRoute = service.hasDirectBusRoute(1, 2);

        //Then
        assertThat(hasDirectRoute).isTrue();
    }

    @Test
    public void shouldReturnFalse() {
        //Given
        when(busRouteDao.hasDirectBusRoute(anyInt(), anyInt())).thenReturn(false);

        //When
        boolean hasDirectRoute = service.hasDirectBusRoute(1, 2);

        //Then
        assertThat(hasDirectRoute).isFalse();
    }
}
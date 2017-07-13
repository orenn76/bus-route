package com.ninyo.bus.route.dao;

import java.io.IOException;

public interface BusRouteDao {

    void initializeBusRoutes(String fileName) throws IOException;

    boolean hasDirectBusRoute(int dep_sid, int arr_sid);
}

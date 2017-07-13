package com.ninyo.bus.route.dao;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BusRouteDaoImpl implements BusRouteDao {

    private final Map<Integer, Map<Integer, Integer>> stationBusRoutes = new HashMap<>();
    private static final int MIN_LENGTH = 3;
    private static final String SPACE = " ";

    @Override
    public void initializeBusRoutes(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] routeLine = line.split(SPACE);
                if (routeLine.length >= MIN_LENGTH) {
                    Integer busRouteId = Integer.parseInt(routeLine[0]);
                    String[] stationsIdsStr = Arrays.copyOfRange(routeLine, 1, routeLine.length);
                    List<Integer> stationsIds = Arrays.stream(stationsIdsStr).map(Integer::parseInt).collect(Collectors.toList());
                    addBusRoute(busRouteId, stationsIds);
                }
            }
        }
    }

    @Override
    public boolean hasDirectBusRoute(int depSid, int arrSid) {
        if (depSid == arrSid) {
            return true;
        }
        Map<Integer, Integer> depBusRoutes = stationBusRoutes.get(depSid);
        if (depBusRoutes == null || depBusRoutes.isEmpty()) {
            return false;
        }
        Map<Integer, Integer> arrBusRoutes = stationBusRoutes.get(arrSid);
        return !(arrBusRoutes == null || arrBusRoutes.isEmpty()) && depBusRoutes.entrySet().stream().anyMatch(depBusRoute ->
                arrBusRoutes.containsKey(depBusRoute.getKey()) && depBusRoute.getValue() < arrBusRoutes.get(depBusRoute.getKey()));
    }

    private void addBusRoute(Integer busRouteId, List<Integer> stationsIds) {
        for (int i = 0; i < stationsIds.size(); i++) {
            stationBusRoutes.computeIfAbsent(stationsIds.get(i), stationId -> new HashMap<>()).put(busRouteId, i);
        }
    }
}

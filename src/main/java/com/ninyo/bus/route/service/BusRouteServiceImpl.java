package com.ninyo.bus.route.service;

import com.ninyo.bus.route.dao.BusRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    @Autowired
    private BusRouteDao busRouteDao;

    @Override
    public boolean hasDirectBusRoute(int depSid, int arrSid) {
        return busRouteDao.hasDirectBusRoute(depSid, arrSid);
    }


}

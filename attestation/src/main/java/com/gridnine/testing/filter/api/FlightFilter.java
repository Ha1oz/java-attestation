package com.gridnine.testing.filter.api;

import com.gridnine.testing.entity.Flight;

public interface FlightFilter {
    boolean isSuitableFlight(Flight flight);
}

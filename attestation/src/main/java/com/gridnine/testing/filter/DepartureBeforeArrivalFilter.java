package com.gridnine.testing.filter;

import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.entity.Flight;

public class DepartureBeforeArrivalFilter implements FlightFilter {
    @Override
    public boolean isSuitableFlight(Flight flight) {
        return flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()));
    }
}

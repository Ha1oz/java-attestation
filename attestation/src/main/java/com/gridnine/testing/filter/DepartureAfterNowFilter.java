package com.gridnine.testing.filter;

import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.entity.Flight;

import java.time.LocalDateTime;

public class DepartureAfterNowFilter implements FlightFilter {

    @Override
    public boolean isSuitableFlight(Flight flight) {
        return flight.getSegments()
                .stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
    }
}

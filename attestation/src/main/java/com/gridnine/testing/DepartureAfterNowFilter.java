package com.gridnine.testing;

import java.time.LocalDateTime;

public class DepartureAfterNowFilter implements FlightFilter {

    @Override
    public boolean isSuitableFlight(Flight flight) {
        return flight.getSegments()
                .stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
    }
}

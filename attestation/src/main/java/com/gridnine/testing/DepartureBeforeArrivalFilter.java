package com.gridnine.testing;

public class DepartureBeforeArrivalFilter implements FlightFilter {
    @Override
    public boolean isSuitableFlight(Flight flight) {
        return flight.getSegments()
                .stream()
                .anyMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()));
    }
}

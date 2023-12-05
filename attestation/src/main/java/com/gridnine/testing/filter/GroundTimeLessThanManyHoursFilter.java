package com.gridnine.testing.filter;

import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class GroundTimeLessThanManyHoursFilter implements FlightFilter {
    private final int hours;

    public GroundTimeLessThanManyHoursFilter(int groundHours) {
        if (groundHours <= 0) {
            throw new IllegalArgumentException("Ground hours must be positive!");
        }

        hours = groundHours;
    }

    @Override
    public boolean isSuitableFlight(Flight flight) {
        List<Segment> segments = flight.getSegments();
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime currentArrival = segments.get(i).getArrivalDate();
            LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
            Duration groundTime = Duration.between(currentArrival, nextDeparture);
            if (groundTime.toHours() < hours) {
                return true;
            }
        }
        return false;
    }
}

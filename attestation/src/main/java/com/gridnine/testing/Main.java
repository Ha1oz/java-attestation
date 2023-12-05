package com.gridnine.testing;


import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.filter.DepartureAfterNowFilter;
import com.gridnine.testing.filter.DepartureBeforeArrivalFilter;
import com.gridnine.testing.filter.GroundTimeLessThanManyHoursFilter;
import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.service.FlightFilterService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("flights = " + flights);

        FlightFilter filter = new DepartureAfterNowFilter();
        FlightFilter filter2 = new DepartureBeforeArrivalFilter();
        FlightFilter filter3 = new GroundTimeLessThanManyHoursFilter(2);
        List<FlightFilter> filters = List.of(filter, filter2, filter3);

        System.out.println("filteredFlights  = " + FlightFilterService.filterFlights(flights, filters));
    }
}
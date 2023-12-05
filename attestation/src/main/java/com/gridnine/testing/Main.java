package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("flights = " + flights);

        FlightFilter filter = new DepartureAfterNowFilter();
        FlightFilter filter2 = new DepartureBeforeArrivalFilter();
        FlightFilter filter3 = new GroundTimeLessThanManyHoursFilter(2);
        List<FlightFilter> filters = List.of(filter, filter2, filter3);

        FlightFilterService service = new FlightFilterService(filters);
        System.out.println("filteredFlights  = " + service.filterFlights(flights));
    }
}
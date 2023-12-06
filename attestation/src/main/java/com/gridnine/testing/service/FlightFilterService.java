package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.filter.api.FlightFilter;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterService {
    private final List<FlightFilter> serviceFilters;
    public FlightFilterService(List<FlightFilter> filters) {
        serviceFilters = filters;
    }
    public List<Flight> filterFlights (List<Flight> flightsForFiltration) {
        return filterFlights(flightsForFiltration, serviceFilters);
    }
    public static List<Flight> filterFlights(List<Flight> flightsForFiltration, List<FlightFilter> filters) {

        return flightsForFiltration
                .stream()
                .filter(f -> filters.stream().parallel().allMatch(flightFilter -> flightFilter.isSuitableFlight(f)))
                .collect(Collectors.toList());
    }
}

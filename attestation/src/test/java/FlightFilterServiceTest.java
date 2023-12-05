import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.filter.DepartureAfterNowFilter;
import com.gridnine.testing.filter.DepartureBeforeArrivalFilter;
import com.gridnine.testing.filter.GroundTimeLessThanManyHoursFilter;
import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.service.FlightFilterService;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterServiceTest {
    @Test
    public void testDepartureAfterNowFilter(){
        FlightFilter departureAfterNowFilter = new DepartureAfterNowFilter();
        Flight wrongDataFlight = new Flight(List.of(new Segment(LocalDateTime.now().minusHours(10), LocalDateTime.now().plusHours(3))));
        Flight correctDataFlight = new Flight(List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))));

        List<FlightFilter> filters = List.of(departureAfterNowFilter);
        List<Flight> flights = List.of(wrongDataFlight, correctDataFlight);

        List<Flight> filteredFlights = FlightFilterService.filterFlights(flights, filters);

        Assert.assertFalse(filteredFlights.contains(wrongDataFlight));
        Assert.assertTrue(filteredFlights.contains(correctDataFlight));
    }
    @Test
    public void testDepartureBeforeArrivalFilter(){
        FlightFilter departureAfterNowFilter = new DepartureBeforeArrivalFilter();
        Flight wrongDataFlight = new Flight(List.of(new Segment(LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(3))));
        Flight correctDataFlight = new Flight(List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))));

        List<FlightFilter> filters = List.of(departureAfterNowFilter);
        List<Flight> flights = List.of(wrongDataFlight, correctDataFlight);

        List<Flight> filteredFlights = FlightFilterService.filterFlights(flights, filters);

        Assert.assertFalse(filteredFlights.contains(wrongDataFlight));
        Assert.assertTrue(filteredFlights.contains(correctDataFlight));
    }
    @Test
    public void testGroundTimeLessThan2HoursFilter(){
        int groundTimeInHours = 2;
        FlightFilter departureAfterNowFilter = new GroundTimeLessThanManyHoursFilter(groundTimeInHours);
        Flight wrongDataFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(10)),
                new Segment(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(3))));
        Flight correctDataFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)),
                new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(8))));

        List<FlightFilter> filters = List.of(departureAfterNowFilter);
        List<Flight> flights = List.of(wrongDataFlight, correctDataFlight);

        List<Flight> filteredFlights = FlightFilterService.filterFlights(flights, filters);

        Assert.assertFalse(filteredFlights.contains(wrongDataFlight));
        Assert.assertTrue(filteredFlights.contains(correctDataFlight));
    }
}

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.filter.DepartureAfterNowFilter;
import com.gridnine.testing.filter.DepartureBeforeArrivalFilter;
import com.gridnine.testing.filter.api.FlightFilter;
import com.gridnine.testing.service.FlightFilterService;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class FlightFilterServiceTest {
    @Test
    public void testFilterFlights() {
        // Создаем моки для фильтров
        FlightFilter departureAfterNowFilter = Mockito.mock(DepartureAfterNowFilter.class);
        FlightFilter departureBeforeArrivalFilter = Mockito.mock(DepartureBeforeArrivalFilter.class);

        // Создаем список фильтров
        List<FlightFilter> filters = Arrays.asList(departureAfterNowFilter, departureBeforeArrivalFilter);

        // Создаем список рейсов
        Flight flight1 = new Flight(List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))));
        Flight flight2 = new Flight(List.of(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))));
        List<Flight> flights = Arrays.asList(flight1, flight2);

        // Устанавливаем поведение моков
        when(departureAfterNowFilter.isSuitableFlight(flight1)).thenReturn(true);
        when(departureAfterNowFilter.isSuitableFlight(flight2)).thenReturn(false);
        when(departureBeforeArrivalFilter.isSuitableFlight(flight1)).thenReturn(true);
        when(departureBeforeArrivalFilter.isSuitableFlight(flight2)).thenReturn(false);

        // Вызываем метод для тестирования
        List<Flight> filteredFlights = FlightFilterService.filterFlights(flights, filters);

        // Проверяем результат
        assertEquals(1, filteredFlights.size());
        assertEquals(flight1, filteredFlights.get(0));
    }
}

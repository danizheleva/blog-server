package com.danielazheleva.blog.services.Impl


import com.danielazheleva.blog.repository.DayRepository
import com.danielazheleva.blog.repository.TripRepository
import com.danielazheleva.blog.services.TripService
import com.danielazheleva.blog.shared.TripDto
import org.junit.Test
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired


class TripImplTest {

    @Autowired
    private TripService tripService;

    @Mock
    private DayRepository dayRepoMock;

    @Mock
    private TripRepository tripRepoMock;

    @Test
    void testGetAllTrips() {

        Mockito.when(tripRepoMock.findAll()).thenReturn(null);

        List<TripDto> foundTrips = tripService.getAllTrips();

    }
}

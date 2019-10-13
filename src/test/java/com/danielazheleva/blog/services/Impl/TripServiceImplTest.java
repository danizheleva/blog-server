package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.TripEntity;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.shared.TripDto;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TripServiceImplTest {

    @InjectMocks
    TripServiceImpl tripService;

    @Mock
    TripRepository tripRepositoryMock;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTrips() {

        TripEntity mockTrip = new TripEntity();
        mockTrip.setTripTitle("Test Trip");
        mockTrip.setId(1L);

        List<TripEntity> mockFoundList = new ArrayList<>();
        mockFoundList.add(mockTrip);

        when(tripRepositoryMock.findAll()).thenReturn(mockFoundList);

        List<TripDto> foundTrips = tripService.getAllTrips();

        assertNotNull(foundTrips);
    }
}
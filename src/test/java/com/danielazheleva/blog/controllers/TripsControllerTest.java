package com.danielazheleva.blog.controllers;

import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.models.responce.TripRest;
import com.danielazheleva.blog.services.DayService;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TripsControllerTest {

    @InjectMocks
    TripsController tripsController;

    @Mock
    TripService tripServiceMock;

    @Mock
    DayService dayService;

    private TripDto mockTripDto = new TripDto();
    private DayDto mockDayDto = new DayDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Date date = new Date(System.currentTimeMillis());

        mockTripDto.setTripTitle("Mocked_Trip_Title");
        mockTripDto.setPostEditDate(date);
        mockTripDto.setPostCreationDate(date);
        mockTripDto.setTripStartDate(date);

        DayDto day1 = generateDayDto("mock_country", "mock_city", 1, date, mockTripDto);
        DayDto day2 = generateDayDto("mock_country", "mock_city", 2, date, mockTripDto);
        List<DayDto> listOfDays = Arrays.asList(day1, day2);

        mockTripDto.setListOfDays(listOfDays);
    }

    @Test
    void testGetAllTrips() {
        when(tripServiceMock.getAllTrips()).thenReturn(Arrays.asList(mockTripDto, mockTripDto));

        List<TripRest> returned = tripsController.getAllTrips();

        assertEquals(returned.size(), 2);
        assertEquals(returned.get(0).getTripTitle(), mockTripDto.getTripTitle());
        assertEquals(returned.get(0).getPostEditDate(), mockTripDto.getPostEditDate());
        assertEquals(returned.get(0).getTripStartDate(), mockTripDto.getTripStartDate());
        assertEquals(returned.get(0).getPostCreationDate(), mockTripDto.getPostCreationDate());
        assertEquals(returned.get(0).getListOfDays().size(), mockTripDto.getListOfDays().size());

    }

    @Test
    void testCreateNewTrip() {
        when(tripServiceMock.saveTrip(any(TripDetailRequestModel.class))).thenReturn(mockTripDto);

        TripRest returned = tripsController.createNewTrip(any(TripDetailRequestModel.class));

        assertEquals(returned.getTripTitle(), mockTripDto.getTripTitle());
        assertEquals(returned.getPostEditDate(), mockTripDto.getPostEditDate());
        assertEquals(returned.getTripStartDate(), mockTripDto.getTripStartDate());
        assertEquals(returned.getPostCreationDate(), mockTripDto.getPostCreationDate());
        assertEquals(returned.getListOfDays().size(), mockTripDto.getListOfDays().size());
    }

    @Test
    void getTrip() {
        when(tripServiceMock.getTrip(anyLong())).thenReturn(mockTripDto);

        TripRest returned = tripsController.getTrip(anyLong());

        assertEquals(returned.getTripTitle(), mockTripDto.getTripTitle());
        assertEquals(returned.getPostEditDate(), mockTripDto.getPostEditDate());
        assertEquals(returned.getTripStartDate(), mockTripDto.getTripStartDate());
        assertEquals(returned.getPostCreationDate(), mockTripDto.getPostCreationDate());
        assertEquals(returned.getListOfDays().size(), mockTripDto.getListOfDays().size());
    }

    @Test
    void editTripDetails() {
        Date date = new Date(System.currentTimeMillis() + 100);

        TripDto newTripDto = new TripDto();
        newTripDto.setTripTitle("New_trip_title");
        newTripDto.setPostCreationDate(date);
        newTripDto.setPostEditDate(date);
        newTripDto.setTripStartDate(date);
        newTripDto.setListOfDays(null);

        when(tripServiceMock.editTripDetails(any(TripDetailRequestModel.class), anyLong())).thenReturn(newTripDto);

        TripRest returned = tripsController.editTripDetails(any(TripDetailRequestModel.class), anyLong());

        assertEquals(returned.getTripTitle(), newTripDto.getTripTitle());
        assertEquals(returned.getPostEditDate(), newTripDto.getPostEditDate());
        assertEquals(returned.getTripStartDate(), newTripDto.getTripStartDate());
        assertEquals(returned.getPostCreationDate(), newTripDto.getPostCreationDate());
        assertEquals(returned.getListOfDays().size(), newTripDto.getListOfDays().size());
    }

//    @Test
//    void getDaysOfTrip() {
//    }
//
//    @Test
//    void getDay() {
//    }
//
//    @Test
//    void createDay() {
//    }
//
//    @Test
//    void editDay() {
//    }
//
//    @Test
//    void deleteDay() {
//    }

    private DayDto generateDayDto(String country, String city, int dayNum, Date date, TripDto tripDto){
        DayDto toReturn = new DayDto();
        toReturn.setCountry(country);
        toReturn.setCity(city);
        toReturn.setDayNumber(dayNum);
        toReturn.setTripDetail(tripDto);

        return toReturn;
    }
}
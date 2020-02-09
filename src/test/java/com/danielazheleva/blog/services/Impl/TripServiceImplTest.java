package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.DayEntity;
import com.danielazheleva.blog.entity.TripEntity;
import com.danielazheleva.blog.exceptions.TripServiceException;
import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.models.request.TripDetailRequestModel;
import com.danielazheleva.blog.repository.TripRepository;
import com.danielazheleva.blog.shared.TripDto;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TripServiceImplTest {

    @InjectMocks
    TripServiceImpl tripService;

    @Mock
    TripRepository tripRepositoryMock;

    private String mockCity = "mock_city";
    private String mockCountry = "mock_country";
    private String mockPostBody = "I_went_on_holiday_yay";
    private String tripTitle = "Test Trip";
    private TripEntity mockTripEntity = new TripEntity();
    private Date aDate = new Date(System.currentTimeMillis());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Create one mock TripEntity
        mockTripEntity.setTripTitle(tripTitle);
        mockTripEntity.setListOfDays(generateMockDayEntityList());
        mockTripEntity.setTripStartDate(aDate);
        mockTripEntity.setPostCreationDate(aDate);
        mockTripEntity.setPostEditDate(aDate);
        mockTripEntity.setId(1L);
    }

    @Test
    void testGetAllTrips() {
        List<TripEntity> mockFoundList = new ArrayList<>();
        mockFoundList.add(mockTripEntity);

        when(tripRepositoryMock.findAll()).thenReturn(mockFoundList);

        List<TripDto> foundTrips = tripService.getAllTrips();

        assertNotNull(foundTrips);
        assertEquals(foundTrips.size(), 1);
        assertEquals(foundTrips.get(0).getTripTitle(), mockTripEntity.getTripTitle());
    }

    @Test
    void testGetAllTrips_notFound() {
        when(tripRepositoryMock.findAll()).thenReturn(null);

        assertThrows(TripServiceException.class,
                () -> tripService.getAllTrips());
    }

    @Test
    void testGetOneTrip() {
       when(tripRepositoryMock.getOne(anyLong())).thenReturn(mockTripEntity);

        TripDto foundTripDto = tripService.getTrip(anyLong());

        assertNotNull(foundTripDto);
        assertEquals(foundTripDto.getTripTitle(), mockTripEntity.getTripTitle());
        assertEquals(foundTripDto.getId(), mockTripEntity.getId());
        assertEquals(foundTripDto.getPostCreationDate(), mockTripEntity.getPostCreationDate());
        assertEquals(foundTripDto.getPostEditDate(), mockTripEntity.getPostCreationDate());
        assertEquals(foundTripDto.getTripStartDate(), mockTripEntity.getTripStartDate());
    }

    @Test
    void testOneAllTrips_notFound() {
        when(tripRepositoryMock.getOne(anyLong())).thenReturn(null);

        assertThrows(TripServiceException.class,
                () -> tripService.getTrip(anyLong()));
    }

    @Test
    void testSaveTrip() {

        when(tripRepositoryMock.save(any(TripEntity.class))).thenReturn(mockTripEntity);

        TripDto savedTrip = tripService.saveTrip(generateMockTripRequestModel());

        assertNotNull(savedTrip);
        assertEquals(savedTrip.getTripTitle(), mockTripEntity.getTripTitle());
        assertEquals(savedTrip.getTripStartDate(), mockTripEntity.getTripStartDate());
        assertEquals(savedTrip.getPostEditDate(), mockTripEntity.getPostEditDate());
        assertEquals(savedTrip.getPostCreationDate(), mockTripEntity.getPostCreationDate());
        assertEquals(savedTrip.getListOfDays().size(), mockTripEntity.getListOfDays().size());
    }


    @Test
    void testDeleteTrip() {
        when(tripRepositoryMock.getOne(anyLong())).thenReturn(mockTripEntity);
        doNothing().when(tripRepositoryMock).deleteById(anyLong());

        tripService.deleteTrip(anyLong());

        Mockito.verify(tripRepositoryMock, times(1)).deleteById(anyLong());

    }

    @Test
    void editTripDetails_noTripFound() {
        when(tripRepositoryMock.getOne(anyLong())).thenReturn(null);

        Mockito.verify(tripRepositoryMock, times(0)).save(any(TripEntity.class));

        assertThrows(TripServiceException.class,
                () -> tripService.editTripDetails(new TripDetailRequestModel(), anyLong()));

    }

    @Test
    void testUpdateTripEntity() {
        Date newDate = new Date(System.currentTimeMillis() + 10000);

        TripDetailRequestModel newTripDetails = new TripDetailRequestModel();
        newTripDetails.setTripTitle("new_title");
        newTripDetails.setTripStartDate(newDate);
        newTripDetails.setPostEditDate(newDate);
        newTripDetails.setPostCreationDate(newDate);
        newTripDetails.setListOfDays(null);

        TripEntity returned = tripService.updateTripEntity(mockTripEntity, newTripDetails);

        assertEquals(returned.getTripTitle(), "new_title");
        assertEquals(returned.getListOfDays(), mockTripEntity.getListOfDays());
        assertEquals(returned.getPostEditDate(), newDate);
        assertEquals(returned.getPostCreationDate(), mockTripEntity.getPostCreationDate());
        assertEquals(returned.getTripStartDate(), mockTripEntity.getTripStartDate());
    }

    @Test
    void testDeleteTrip_NoTripFound() {
        when(tripRepositoryMock.getOne(anyLong())).thenReturn(null);

        assertThrows(TripServiceException.class,
                () -> tripService.deleteTrip(anyLong()));
        verify(tripRepositoryMock, times(0)).deleteById(anyLong());
    }

    private List<DayEntity> generateMockDayEntityList() {
        DayEntity dayEntity1 = new DayEntity();
        dayEntity1.setId(1L);

        dayEntity1.setPostText(mockPostBody);
        dayEntity1.setDayNumber(1);

        DayEntity dayEntity2 = new DayEntity();
        dayEntity2.setId(2L);
        dayEntity2.setPostText(mockPostBody);
        dayEntity2.setDayNumber(2);

        List<DayEntity> toReturn = new ArrayList<>();
        toReturn.add(dayEntity1);
        toReturn.add(dayEntity2);

        return toReturn;
    }

    private TripDetailRequestModel generateMockTripRequestModel() {
        TripDetailRequestModel toReturn = new TripDetailRequestModel();
        toReturn.setTripTitle(tripTitle);
        toReturn.setPostCreationDate(aDate);
        toReturn.setPostEditDate(aDate);
        toReturn.setTripStartDate(aDate);

        List<DayRequestModel> listOfDays = new ArrayList<>();
        listOfDays.add(generateMockDayRequestModel(1));
        listOfDays.add(generateMockDayRequestModel(2));

        toReturn.setListOfDays(listOfDays);

        return toReturn;
    }

    private DayRequestModel generateMockDayRequestModel(int dayNumber) {
        DayRequestModel toReturn = new DayRequestModel();
        toReturn.setPostText(mockPostBody);
        toReturn.setDayNumber(dayNumber);

        return toReturn;
    }


}
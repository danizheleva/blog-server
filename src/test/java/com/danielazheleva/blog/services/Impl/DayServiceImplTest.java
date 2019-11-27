package com.danielazheleva.blog.services.Impl;

import com.danielazheleva.blog.entity.DayEntity;
import com.danielazheleva.blog.entity.TripEntity;
import com.danielazheleva.blog.exceptions.DayServiceException;
import com.danielazheleva.blog.models.request.DayRequestModel;
import com.danielazheleva.blog.repository.DayRepository;
import com.danielazheleva.blog.services.TripService;
import com.danielazheleva.blog.shared.DayDto;
import com.danielazheleva.blog.shared.TripDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class DayServiceImplTest {

    @InjectMocks
    DayServiceImpl dayService;

    @Mock
    DayRepository dayRepoMock;

    @Mock
    TripService tripServiceMock;

    private TripDto mockTripDto = new TripDto();
    private DayEntity mockDayEntity = new DayEntity();
    private Date date = new Date(System.currentTimeMillis());
    private ModelMapper mm = new ModelMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        DayDto day1 = generateMockDayDto("city1", "mock_title", "country1", 1, "text1");
        DayDto day2 = generateMockDayDto("city2","mock_title","country2", 2, "text2");
        List<DayDto> listOfDays = new ArrayList<>();
        listOfDays.add(day1);
        listOfDays.add(day2);

        mockTripDto.setTripTitle("mock_title");
        mockTripDto.setId(1L);
        mockTripDto.setPostCreationDate(date);
        mockTripDto.setPostEditDate(date);
        mockTripDto.setTripStartDate(date);
        mockTripDto.setListOfDays(listOfDays);

        mockDayEntity.setDayNumber(1);
        mockDayEntity.setCity("mock_city");
        mockDayEntity.setCountry("mock_country");
        mockDayEntity.setPostText("mock_text_about_a_day");
        mockDayEntity.setTripDetail(mm.map(mockTripDto, TripEntity.class));


    }

    @Test
    void testGetAllDaysForTrip() {

        when(tripServiceMock.getTrip(anyLong())).thenReturn(mockTripDto);

        List<DayDto> returned = dayService.getAllDaysForTrip(anyLong());

        assertThat(returned)
                .isEqualTo(mockTripDto.getListOfDays())
                .isNotNull()
                .size().isEqualTo(mockTripDto.getListOfDays().size());
    }

    @Test
    void testGetAllDaysForTrip_isNull() {

        when(tripServiceMock.getTrip(anyLong())).thenReturn(null);

        assertThrows(DayServiceException.class,
        () -> dayService.getAllDaysForTrip(anyLong()));

    }

    @Test
    void getDay() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(mockDayEntity);

        DayDto returned = dayService.getDay(anyLong());

        assertEquals(returned.getCity(), mockDayEntity.getCity());
        assertEquals(returned.getCountry(), mockDayEntity.getCountry());
        assertEquals(returned.getDayNumber(), mockDayEntity.getDayNumber());
        assertEquals(returned.getPostText(), mockDayEntity.getPostText());
    }

    @Test
    void getDay_isNull() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(null);

        assertThrows(DayServiceException.class,
                () -> dayService.getAllDaysForTrip(anyLong()));
    }

    @Test
    void testEditDay() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(mockDayEntity);
        when(tripServiceMock.getTrip(anyLong())).thenReturn(mockTripDto);

        DayRequestModel newDetails = generateMockDayRequestModel();
        Long anyLong = anyLong();
        DayDto returnedEditedDay = dayService.editDay(newDetails, anyLong, anyLong);

        assertEquals(returnedEditedDay.getPostText(), newDetails.getPostText());
        assertEquals(returnedEditedDay.getDayNumber(), newDetails.getDayNumber());
        assertEquals(returnedEditedDay.getCountry(), newDetails.getCountry());
        assertEquals(returnedEditedDay.getCity(), newDetails.getCity());

        verify(dayRepoMock, times(1)).save(any(DayEntity.class));
    }

    @Test
    void testEditDay_noDayFound() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(null);
        DayRequestModel newDetails = generateMockDayRequestModel();
        Long anyLong = anyLong();

        assertThrows(DayServiceException.class,
                () -> dayService.editDay(newDetails, anyLong, anyLong));

        verify(dayRepoMock, times(0)).save(any(DayEntity.class));
    }

    @Test
    void testEditDay_dayNumberOutOfBound() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(mockDayEntity);
        when(tripServiceMock.getTrip(anyLong())).thenReturn(mockTripDto);

        DayRequestModel newDetails = generateMockDayRequestModel();
        newDetails.setDayNumber(mockTripDto.getListOfDays().size() + 1);
        Long anyLong = anyLong();

        assertThrows(DayServiceException.class,
                () -> dayService.editDay(newDetails, anyLong, anyLong));

        verify(dayRepoMock, times(0)).save(any(DayEntity.class));
    }

    @Test
    void testCreateDay() {
        when(tripServiceMock.getTrip(anyLong())).thenReturn(mockTripDto);
        when(dayRepoMock.save(any(DayEntity.class))).thenReturn(mockDayEntity);

        DayRequestModel newDay = generateMockDayRequestModel();
        TripDto returned = dayService.createDay(newDay, anyLong());

        verify(dayRepoMock, times(1)).save(any(DayEntity.class));

        assertThat(returned.getListOfDays().get(2).getPostText())
                .isEqualTo(newDay.getPostText());

        assertEquals(returned.getListOfDays().size(), mockTripDto.getListOfDays().size() + 1 );

    }

    @Test
    void deleteDay() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(mockDayEntity);
        doNothing().when(dayRepoMock).deleteById(anyLong());

        dayService.deleteDay(anyLong());

        verify(dayRepoMock, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteDay_isNull() {
        when(dayRepoMock.getOne(anyLong())).thenReturn(null);

        verify(dayRepoMock, times(0)).deleteById(anyLong());
        assertThrows(DayServiceException.class,
                () -> dayService.deleteDay(anyLong()));
    }

    private DayDto generateMockDayDto(String city, String title, String country, int dayNumber, String text) {
        DayDto day = new DayDto();
        day.setCity(city);
        day.setDayTitle(title);
        day.setCountry(country);
        day.setTripDetail(mockTripDto);
        day.setDayNumber(dayNumber);
        day.setPostText(text);

        return day;
    }

    private DayRequestModel generateMockDayRequestModel(){
        return new DayRequestModel(1, "test_title","test_country", "test_city", "fake_text");
    }

}
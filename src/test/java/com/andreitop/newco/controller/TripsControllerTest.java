package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TripsController.class)
public class TripsControllerTest {

    private static final String TRIP_JSON = "{\"origin\": \"LED\" , \"destination\":\"MOW\", \"price\" : 12256}";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String API_URL = ApiConstant.API_V_1 + "/trips";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    private TripDto tripDto = new TripDto();


    @Test
    public void whenPostTrip_thenCreateTrip() throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenTrips_whenGetTrips_thenReturnJsonArray() throws Exception {

        tripDto.setId(1L);
        tripDto.setOrigin("MOW");
        tripDto.setDestination("LED");
        tripDto.setPrice(4232);

        List<TripDto> allTrips = Collections.singletonList(tripDto);
        given(tripService.findAll()).willReturn(allTrips);

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].origin", is("MOW")))
                .andExpect(jsonPath("$[0].destination", is("LED")))
                .andExpect(jsonPath("$[0].price", is(4232)));
    }

    @Test
    public void testFindById() throws Exception {
        tripDto.setOrigin("NYC");
        tripDto.setDestination("SPB");
        tripDto.setPrice(80000);

        long id = tripService.save(tripDto);

        tripDto.setId(id);

        given(tripService.findById(id)).willReturn(tripDto);

        mockMvc.perform(get(API_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.origin", is("NYC")))
                .andExpect(jsonPath("$.destination", is("SPB")))
                .andExpect(jsonPath("$.price", is(80000)));

    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        tripDto.setOrigin("NYC");
        tripDto.setDestination("SPB");
        tripDto.setPrice(80000);

        long id = tripService.save(tripDto);

        tripDto.setId(id);

        mockMvc.perform(delete(API_URL + "/" + id)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isNoContent());

    }



}
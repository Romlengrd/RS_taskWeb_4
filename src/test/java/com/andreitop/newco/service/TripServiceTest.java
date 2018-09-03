package com.andreitop.newco.service;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceTest {

    @MockBean
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    private TripDto tripDto = new TripDto();


    @Test
    public void testFindAll() {
        tripDto.setId(0L);
        tripDto.setOrigin("LDN");
        tripDto.setDestination("SPB");
        tripDto.setPrice(50_000);
        long id = tripRepository.save(tripDto);
        tripDto.setId(id);
        List<TripDto> allTrips = Collections.singletonList(tripDto);
        given(tripService.findAll()).willReturn(allTrips);
    }

    @Test
    public void testFindById() {
        tripDto.setOrigin("LDN");
        tripDto.setDestination("SPB");
        tripDto.setPrice(50_000);

        long id = tripService.save(tripDto);

        tripDto.setId(id);

        given(tripService.findById(id)).willReturn(tripDto);
    }

    @Test
    public void testSave() {
        tripDto.setOrigin("LDN");
        tripDto.setDestination("SPB");
        tripDto.setPrice(50_000);
        long id=0;

        given(tripService.save(tripDto)).willReturn(id);
    }

    @Test
    public void testDelete() {
        tripDto.setOrigin("LDN");
        tripDto.setDestination("SPB");
        tripDto.setPrice(50_000);
        long id = tripService.save(tripDto);
        tripService.delete(id);
        List<TripDto> list= tripService.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void testUpdate() {
        tripDto.setOrigin("LDN");
        tripDto.setDestination("SPB");
        tripDto.setPrice(50_000);
        long id = tripService.save(tripDto);
        tripDto.setId(id);
        tripDto.setPrice(60_000);
        tripService.update(tripDto);
        assertThat(tripDto.getPrice()).isEqualTo(60_000);
    }
}
package com.apisaes.task.mvc.services;

import com.apisaes.task.mvc.repository.DataDetailsRepositoryDeprecated;
import hr.aaa.test.v0.datadetails.DataDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataDetailsServiceImplTest {

    @Mock
    DataDetailsRepositoryDeprecated dataDetailsRepository;

    DataDetailsServiceDeprecated dataDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dataDetailsService = new DataDetailsServiceDeprecatedImpl(dataDetailsRepository);
    }

    @Test
    void findAllDataDetails() {
        when(dataDetailsRepository.findAllDataDetails()).thenReturn(
                        Arrays.asList(new DataDetails(), new DataDetails())
        );

        List<DataDetails> list = dataDetailsService.findAllDataDetails();

        verify(dataDetailsRepository, times(1)).findAllDataDetails();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertNotEquals(0, list.size());
    }

    @Test
    void findById() {
        DataDetails defaultDataDetails = new DataDetails();

        when(dataDetailsRepository.findById(any())).thenReturn(
                new DataDetails()
        );

        DataDetails dataDetails = dataDetailsService.findById("1");

        verify(dataDetailsRepository, times(1)).findById(any());
        assertNotNull(dataDetails);
    }

    @Test
    void findByOperationId() {
        when(dataDetailsRepository.findAllByOperationId(any())).thenReturn(
                Arrays.asList(new DataDetails(), new DataDetails())
        );

        List<DataDetails> list = dataDetailsService.findByOperationId("2");

        verify(dataDetailsRepository, times(1)).findAllByOperationId(any());
        assertNotNull(list);
        assertEquals(2, list.size());
        assertNotEquals(0, list.size());
    }

    @Test
    void saveDataDetails() {
        DataDetails givenDataDetail = new DataDetails();
        when(dataDetailsRepository.saveDataDetails(any())).thenReturn(
                givenDataDetail
        );

        DataDetails dataDetails = dataDetailsService.saveDataDetails(givenDataDetail);

        verify(dataDetailsRepository, times(1)).saveDataDetails(any());
        assertNotNull(dataDetails);
        assertEquals(dataDetails, givenDataDetail);
    }
}
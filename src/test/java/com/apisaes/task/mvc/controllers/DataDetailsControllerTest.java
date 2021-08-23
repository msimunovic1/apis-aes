package com.apisaes.task.mvc.controllers;

import com.apisaes.task.mvc.services.DataDetailsService;
import hr.aaa.test.v0.datadetails.DataDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class DataDetailsControllerTest {

    @Mock
    DataDetailsService dataDetailsService;

    @Mock
    Model model;

    DataDetailsController controller;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new DataDetailsController(dataDetailsService);
    }

    @Disabled
    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/datadetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("dataDetailsList"));
    }


    @Disabled
    @Test
    void listDataDetails() {
        //given
        when(dataDetailsService.findAllDataDetails()).thenReturn(
                Arrays.asList(new DataDetails(), new DataDetails())
        );

        ArgumentCaptor<List<DataDetails>> argumentCaptor =
                ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.listDataDetails(model);

        //then
        assertEquals("dataDetailsList", viewName);
        verify(dataDetailsService, times(1)).findAllDataDetails();
        verify(model, times(1)).addAttribute(
                eq("listDataDetails"),
                argumentCaptor.capture()
        );

        List<DataDetails> listInController = argumentCaptor.getValue();
        assertEquals(2, listInController.size());
    }

    @Disabled
    @Test
    void findDataDetailsById() {
        //given
        when(dataDetailsService.findById(anyString())).thenReturn(
                new DataDetails()
        );

        ArgumentCaptor<DataDetails> argumentCaptor =
                ArgumentCaptor.forClass(DataDetails.class);

        //when
        String viewName = controller.findDataDetailsById(model, "1");

        //then
        assertEquals("dataDetailsList", viewName);
        verify(dataDetailsService, times(1)).findById("1");
        verify(model, times(1)).addAttribute(
                eq("listDataDetails"),
                argumentCaptor.capture()
        );

        DataDetails dataInController = argumentCaptor.getValue();
        assertNotNull(dataInController);
    }

    @Disabled
    @Test
    void getForm() {

    }

    @Disabled
    @Test
    void createDataDetails() {

    }
}
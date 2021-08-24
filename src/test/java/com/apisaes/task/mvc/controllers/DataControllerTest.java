package com.apisaes.task.mvc.controllers;

import com.apisaes.task.dataDetails.service.DataDetailsService;
import com.apisaes.task.mvc.commands.DataDetailsCommand;
import com.apisaes.task.mvc.commands.DataTypeCommand;
import com.apisaes.task.mvc.commands.OfficeTypeCommand;
import com.apisaes.task.mvc.commands.OperationCommand;
import com.apisaes.task.mvc.converters.Converter;
import com.apisaes.task.mvc.converters.DataDetailsConverter;
import hr.aaa.test.v0.datadetails.DataDetails;
import hr.aaa.test.v0.datadetails.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class DataControllerTest {

    @Mock
    DataDetailsService dataDetailsService;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    DataController controller;

    DataDetailsCommand dataDetailsCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new DataController(dataDetailsService);

        dataDetailsCommand = DataDetailsCommand.builder()
                .data(DataTypeCommand.builder()
                        .operation(OperationCommand.builder()
                                .acceptanceDate("1234-12-12")
                                .arrivalDateTime("1234-12-12T00:00:00")
                                .build())
                        .accountValue(BigDecimal.ONE)
                        .dateOfStart("1234-12-12")
                        .origin(OfficeTypeCommand.builder().build())
                        .destination(OfficeTypeCommand.builder().build())
                        .exporter(OfficeTypeCommand.builder().build())
                        .visitingLocations(new ArrayList<>())
                        .build())
                .build();
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/mvc/data"))
                .andExpect(status().isOk())
                .andExpect(view().name("dataDetailsList"));
    }


    @Test
    void listDataDetails() {
        //given
        when(dataDetailsService.getAllDataDetails()).thenReturn(
                Arrays.asList(new DataDetails(), new DataDetails())
        );

        ArgumentCaptor<List<DataDetails>> argumentCaptor =
                ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.listDataDetails(model);

        //then
        assertEquals("dataDetailsList", viewName);
        verify(dataDetailsService, times(1)).getAllDataDetails();
        verify(model, times(1)).addAttribute(
                eq("listDataDetails"),
                argumentCaptor.capture()
        );

        List<DataDetails> listInController = argumentCaptor.getValue();
        assertEquals(2, listInController.size());
    }

    @Test
    void findDataDetailsById() {
        //given
        when(dataDetailsService.getDataDetailsById(anyLong())).thenReturn(
                new DataDetails()
        );

        ArgumentCaptor<DataDetails> argumentCaptor =
                ArgumentCaptor.forClass(DataDetails.class);

        //when
        String viewName = controller.findDataDetailsById(model, "1");

        //then
        assertEquals("dataDetailsList", viewName);
        verify(dataDetailsService, times(1)).getDataDetailsById(1L);
        verify(model, times(1)).addAttribute(
                eq("listDataDetails"),
                argumentCaptor.capture()
        );

        DataDetails dataInController = argumentCaptor.getValue();
        assertNotNull(dataInController);
    }

    @Test
    void getForm(){
        //when
        String viewName = controller.getForm(model);
        ArgumentCaptor<DataDetailsCommand> argumentCaptor =
                ArgumentCaptor.forClass(DataDetailsCommand.class);

        //then
        assertEquals("newDataDetails", viewName);

        verify(model, times(1)).addAttribute(
                argumentCaptor.capture()
        );
        DataDetailsCommand dataInController = argumentCaptor.getValue();
        assertNotNull(dataInController);
    }

    @Test
    void createDataDetailsSuccess() {
        //given
        DataDetailsConverter dataDetailsConverter = new DataDetailsConverter();
        DataDetails dataDetails = dataDetailsConverter.convert(dataDetailsCommand);

        when(bindingResult.hasErrors()).thenReturn(
                false
        );
        when(dataDetailsService.saveDataDetails(any())).thenReturn(
                dataDetails
        );

        ArgumentCaptor<DataDetails> argumentCaptor =
                ArgumentCaptor.forClass(DataDetails.class);

        //when
        String viewName = controller.createDataDetails(
                dataDetailsCommand,
                bindingResult,
                model
                );

        //then
        assertEquals("redirect:/mvc/data", viewName);
        verify(dataDetailsService, times(1)).saveDataDetails(argumentCaptor.capture());

        verifyNoInteractions(model);

        DataDetails dataInController = argumentCaptor.getValue();
        assertNotNull(dataInController);
    }

    @Test
    void createDataDetailsFail() {
        //given
        DataDetails dataDetails = new DataDetails();

        when(bindingResult.hasErrors()).thenReturn(
                true
        );

        ArgumentCaptor<DataDetailsCommand> argumentCaptor =
                ArgumentCaptor.forClass(DataDetailsCommand.class);

        //when
        String viewName = controller.createDataDetails(
                dataDetailsCommand,
                bindingResult,
                model
        );

        //then
        assertEquals("newDataDetails", viewName);
        verifyNoInteractions(dataDetailsService);

        verify(model, times(1)).addAttribute(
                argumentCaptor.capture()
        );
        DataDetailsCommand dataInController = argumentCaptor.getValue();
        assertNotNull(dataInController);
    }
}
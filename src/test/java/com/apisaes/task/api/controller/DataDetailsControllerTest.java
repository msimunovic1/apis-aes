package com.apisaes.task.api.controller;

import com.apisaes.task.api.exception.MethodNotAllowedException;
import com.apisaes.task.api.exception.NotFoundException;
import com.apisaes.task.dataDetails.service.DataDetailsService;
import com.apisaes.task.util.XmlUtil;
import hr.aaa.test.v0.datadetails.DataDetails;
import hr.aaa.test.v0.datadetails.DataType;
import hr.aaa.test.v0.datadetails.OfficeType;
import hr.aaa.test.v0.datadetails.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataDetailsController.class)
public class DataDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataDetailsService dataDetailsService;

    DataDetails dataDetails = new DataDetails();

    @BeforeEach
    void setUp() throws DatatypeConfigurationException {

        Operation operation1 = new Operation();
        operation1.setID("1");
        LocalDateTime localDate1 = LocalDateTime.of(2019, 9, 19, 10, 10, 10);
        operation1.setArrivalDateTime(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate1.toString()));
        operation1.setStoringFlag("1");
        operation1.setType("b");
        operation1.setAdditionalType("b");
        operation1.setAcceptanceDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate1.toString()));

        Operation operation2 = new Operation();
        operation2.setID("2");
        LocalDateTime localDate2 = LocalDateTime.of(2020, 2, 20,10, 10, 10);
        operation2.setArrivalDateTime(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate2.toString()));
        operation2.setStoringFlag("2");
        operation2.setType("a");
        operation2.setAdditionalType("a");
        operation2.setAcceptanceDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate2.toString()));

        // OFFICES
        OfficeType officeType1 = new OfficeType();
        officeType1.setReferenceNumber("AA1AAd100");

        OfficeType officeType2 = new OfficeType();
        officeType2.setReferenceNumber("BB1AA100");

        OfficeType officeType3 = new OfficeType();
        officeType3.setReferenceNumber("CC1AA100");

        // DATA
        DataType dataType = new DataType();
        dataType.setOperation(operation1);
        dataType.setOrigin(officeType1);
        dataType.setDestination(officeType2);
        dataType.setExporter(officeType3);
        LocalDate localDate3 = LocalDate.of(2020, 3, 3);
        dataType.setDateOfStart(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate3.toString()));
        dataType.setAccountValue(BigDecimal.valueOf(10));

        // DATA DETAILS
        dataDetails.setData(dataType);
    }

    @Test
    void getDataDetailsById_thenReturns200() throws Exception {

        when(dataDetailsService.getDataDetailsById(1L)).thenReturn(dataDetails);

        mockMvc.perform(get("/api/dataDetails/1")
                    .contentType(MediaType.APPLICATION_XML))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().string(XmlUtil.toString(dataDetails, DataDetails.class)))
                .andDo(print());
    }

    @Test
    void getDataDetailsById_thenReturns404() throws Exception {

        when(dataDetailsService.getDataDetailsById(1L)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/api/dataDetails/1")
                        .contentType(MediaType.APPLICATION_XML))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveDataDetails_thenReturns201() throws Exception {

        when(dataDetailsService.saveDataDetails(any())).thenReturn(dataDetails);

        mockMvc.perform(post("/api/dataDetails")
                    .contentType(MediaType.APPLICATION_XML)
                    .accept(MediaType.APPLICATION_XML)
                    .content(XmlUtil.toString(dataDetails, DataDetails.class)))
                .andExpect(status().isCreated())
                .andExpect(content().string(XmlUtil.toString(dataDetails, DataDetails.class)))
                .andDo(print());
    }

    @Test
    void saveDataDetails_thenReturns405() throws Exception {

        when(dataDetailsService.saveDataDetails(any())).thenThrow(MethodNotAllowedException.class);

        mockMvc.perform(post("/api/dataDetails")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(XmlUtil.toString(dataDetails, DataDetails.class)))
                .andExpect(status().isMethodNotAllowed());
    }

}

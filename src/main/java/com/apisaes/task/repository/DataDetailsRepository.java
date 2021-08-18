package com.apisaes.task.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import hr.aaa.test.v0.datadetails.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@Repository
public class DataDetailsRepository {

    private static Long idCounter = 1L;
    private static final Map<String, DataDetailsType> dataDetailsMap = new HashMap<>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {

        // OPERATIONS
        Operation operation1 = new Operation();
        operation1.setID("1");
        LocalDate localDate1 = LocalDate.of(2019, 9, 19);
        operation1.setArrivalDateTime(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate1.toString()));
        operation1.setStoringFlag("1");
        operation1.setType("1");
        operation1.setAdditionalType("A");
        operation1.setAcceptanceDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate1.toString()));

        Operation operation2 = new Operation();
        operation2.setID("2");
        LocalDate localDate2 = LocalDate.of(2020, 2, 20);
        operation2.setArrivalDateTime(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate2.toString()));
        operation2.setStoringFlag("2");
        operation2.setType("2");
        operation2.setAdditionalType("A");
        operation2.setAcceptanceDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate2.toString()));

        // OFFICES
        OfficeType officeType1 = new OfficeType();
        officeType1.setReferenceNumber("CUA1A2A3");

        OfficeType officeType2 = new OfficeType();
        officeType2.setReferenceNumber("CUB1B2B3");

        OfficeType officeType3 = new OfficeType();
        officeType3.setReferenceNumber("CUC1C2C3");

        // DATA
        DataType dataType1 = new DataType();
        dataType1.setOperation(operation1);
        dataType1.setOrigin(officeType1);
        dataType1.setDestination(officeType2);
        dataType1.setExporter(officeType3);
        LocalDate localDate3 = LocalDate.of(2020, 3, 3);
        dataType1.setDateOfStart(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate3.toString()));
        dataType1.setAccountValue(BigDecimal.valueOf(1000));

        DataType dataType2 = new DataType();
        dataType2.setOperation(operation2);
        dataType2.setOrigin(officeType2);
        dataType2.setDestination(officeType3);
        dataType2.setExporter(officeType1);
        LocalDate localDate4 = LocalDate.of(2020, 4, 4);
        dataType2.setDateOfStart(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate4.toString()));
        dataType2.setAccountValue(BigDecimal.valueOf(2000));

        // DATA DETAILS
        DataDetailsType dataDetailsType1 = new DataDetailsType();
        dataDetailsType1.setData(dataType1);

        saveDataDetails(dataDetailsType1);

        DataDetailsType dataDetailsType2 = new DataDetailsType();
        dataDetailsType2.setData(dataType2);

        saveDataDetails(dataDetailsType2);
    }

    public DataDetailsType findDataDetailsByOperationId(String id) {
        return dataDetailsMap.get(id);
    }

    public DataDetailsType saveDataDetails(DataDetailsType dataDetailsType){
        dataDetailsMap.put(
                (idCounter++).toString(),
                dataDetailsType
        );
        return dataDetailsType;
    }

}

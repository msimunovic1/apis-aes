package com.apisaes.task.repository;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import hr.aaa.test.v0.datadetails.*;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

@Component
public class DataDetailsRepository {
    private static final Map<String, DataDetailsType> dataDetailsMap = new HashMap<>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {

        DataDetailsType dataDetails = new DataDetailsType();
        DataType data = new DataType();

        Operation operation = new Operation();
        operation.setID("ABC");
        operation.setArrivalDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2021, 1, 10).toString()));
        operation.setStoringFlag("Y");
        operation.setType("Operation Type");
        operation.setAdditionalType("Additional Type");
        operation.setAcceptanceDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2021, 1, 11).toString()));
        data.setOperation(operation);

        OfficeType origin = new OfficeType();
        origin.setReferenceNumber("1234");
        data.setOrigin(origin);

        OfficeType destination = new OfficeType();
        destination.setReferenceNumber("5678");
        data.setDestination(destination);

        data.setDateOfStart(DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2021, 1, 1).toString()));

        data.setAccountValue(BigDecimal.valueOf(300.00));

        OfficeType exporter = new OfficeType();
        exporter.setReferenceNumber("1289");
        data.setExporter(exporter);

        data.getVisitingLocations().add(origin);
        data.getVisitingLocations().add(exporter);

        dataDetails.setData(data);

        dataDetailsMap.put(operation.getID(), dataDetails);
    }

    public DataDetailsType findDataDetailsByOperationId(String operationId) {
        return dataDetailsMap.get(operationId);
    }

}

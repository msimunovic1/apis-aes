package com.apisaes.task.bootstrapData;

import com.apisaes.task.dataDetails.service.DataDetailsService;
import hr.aaa.test.v0.datadetails.DataDetails;
import hr.aaa.test.v0.datadetails.DataType;
import hr.aaa.test.v0.datadetails.OfficeType;
import hr.aaa.test.v0.datadetails.Operation;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    DataDetailsService dataDetailsService;

    public DataLoader(DataDetailsService dataDetailsService) {
        this.dataDetailsService = dataDetailsService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (dataDetailsService.getAllDataDetails().size() < 1){
            try {
                loadData();
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData() throws DatatypeConfigurationException {
        // OPERATIONS
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
        officeType1.setReferenceNumber("AA1AA100");

        OfficeType officeType2 = new OfficeType();
        officeType2.setReferenceNumber("BB1AA100");

        OfficeType officeType3 = new OfficeType();
        officeType3.setReferenceNumber("CC1AA100");

        // DATA
        DataType dataType1 = new DataType();
        dataType1.setOperation(operation1);
        dataType1.setOrigin(officeType1);
        dataType1.setDestination(officeType2);
        dataType1.setExporter(officeType3);
        LocalDate localDate3 = LocalDate.of(2020, 3, 3);
        dataType1.setDateOfStart(
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDate3.toString()));
        dataType1.setAccountValue(BigDecimal.valueOf(10));

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
        DataDetails DataDetails1 = new DataDetails();
        DataDetails1.setData(dataType1);

        dataDetailsService.saveDataDetails(DataDetails1);

        DataDetails DataDetails2 = new DataDetails();
        DataDetails2.setData(dataType2);

        dataDetailsService.saveDataDetails(DataDetails2);
    }
}

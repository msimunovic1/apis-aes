package com.apisaes.task.mvc.converters;

import com.apisaes.task.mvc.commands.DataTypeCommand;
import hr.aaa.test.v0.datadetails.DataType;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class DataTypeConverter implements Converter<DataType, DataTypeCommand> {

    OperationConverter operationConverter;
    OfficeTypeConverter officeTypeConverter;

    public DataTypeConverter() {
        operationConverter = new OperationConverter();
        officeTypeConverter = new OfficeTypeConverter();
    }

    @Override
    public DataType convertToDomain(DataTypeCommand command) {
        DataType dataType = null;
        try {
            dataType = new DataType();
            dataType.setOperation(
                    operationConverter.convertToDomain(command.getOperation())
            );
            dataType.setOrigin(
                    officeTypeConverter.convertToDomain(command.getOrigin())
            );
            dataType.setDestination(
                    officeTypeConverter.convertToDomain(command.getDestination())
            );
            dataType.setDateOfStart(DatatypeFactory
                    .newInstance()
                    .newXMLGregorianCalendar(
                            command.getDateOfStart()
                    )
            );
            dataType.setAccountValue(command.getAccountValue());
            dataType.setExporter(
                    officeTypeConverter.convertToDomain(command.getExporter())
            );
            dataType.getVisitingLocations();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return dataType;
    }
}

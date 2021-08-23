package com.apisaes.task.mvc.converters;

import com.apisaes.task.mvc.commands.DataDetailsCommand;
import hr.aaa.test.v0.datadetails.DataDetails;

public class DataDetailsConverter {

    DataTypeConverter dataTypeConverter;

    public DataDetailsConverter() {
        dataTypeConverter = new DataTypeConverter();
    }

    public DataDetails convert(DataDetailsCommand command){
        DataDetails dataDetails = new DataDetails();
        dataDetails.setData(
                dataTypeConverter.convertToDomain(command.getData())
        );
        return dataDetails;
    }
}

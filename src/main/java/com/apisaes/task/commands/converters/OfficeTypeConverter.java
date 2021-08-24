package com.apisaes.task.commands.converters;

import com.apisaes.task.commands.OfficeTypeCommand;
import hr.aaa.test.v0.datadetails.OfficeType;

public class OfficeTypeConverter implements Converter<OfficeType, OfficeTypeCommand> {

    @Override
    public OfficeType convertToDomain(OfficeTypeCommand command) {
        OfficeType officeType = new OfficeType();
        officeType.setReferenceNumber(command.getReferenceNumber());
        return officeType;
    }
}

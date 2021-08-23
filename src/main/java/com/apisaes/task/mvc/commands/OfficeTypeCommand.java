package com.apisaes.task.mvc.commands;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OfficeTypeCommand {

    @NotNull
    @Pattern(
            regexp = "[A-Z]{2}[A-Z0-9]{6}",
            message = "Reference number must be in regex format '[A-Z]{2}[A-Z0-9]{6}', example: 'AA1AA100'"
    )
    private String referenceNumber;

    public OfficeTypeCommand() {
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}

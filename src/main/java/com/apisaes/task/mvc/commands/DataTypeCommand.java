package com.apisaes.task.mvc.commands;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

public class DataTypeCommand {
    @Valid
    private OperationCommand operation;
    @Valid
    private OfficeTypeCommand origin;
    @Valid
    private OfficeTypeCommand destination;
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}(\\.\\d+)?",
            message = "Date of start must be in YYYY-MM-DD format."
    )
    private String dateOfStart; //DateType
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=9, fraction=2)
    private BigDecimal accountValue;
    @Valid
    private OfficeTypeCommand exporter;
    @Valid
    private List<OfficeTypeCommand> visitingLocations;

    public DataTypeCommand() {
    }

    public OperationCommand getOperation() {
        return operation;
    }

    public void setOperation(OperationCommand operation) {
        this.operation = operation;
    }

    public OfficeTypeCommand getOrigin() {
        return origin;
    }

    public void setOrigin(OfficeTypeCommand origin) {
        this.origin = origin;
    }

    public OfficeTypeCommand getDestination() {
        return destination;
    }

    public void setDestination(OfficeTypeCommand destination) {
        this.destination = destination;
    }

    public String getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(String dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public BigDecimal getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(BigDecimal accountValue) {
        this.accountValue = accountValue;
    }

    public OfficeTypeCommand getExporter() {
        return exporter;
    }

    public void setExporter(OfficeTypeCommand exporter) {
        this.exporter = exporter;
    }

    public List<OfficeTypeCommand> getVisitingLocations() {
        return visitingLocations;
    }

    public void setVisitingLocations(List<OfficeTypeCommand> visitingLocations) {
        this.visitingLocations = visitingLocations;
    }
}

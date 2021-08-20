package com.apisaes.task.commands.v0;

import java.util.List;

public class DataTypeCommand {
    private OperationCommand operation;
    private OfficeTypeCommand origin;
    private OfficeTypeCommand destination;
    private String dateOfStart; //DateType
    private Long accountValue;
    private OfficeTypeCommand exporter;
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

    public Long getAccountValue() {
        return accountValue;
    }

    public void setAccountValue(Long accountValue) {
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

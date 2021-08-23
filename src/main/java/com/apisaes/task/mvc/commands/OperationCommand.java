package com.apisaes.task.mvc.commands;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.regex.Matcher;

public class OperationCommand {

    @NotNull
    @Pattern(
            regexp = ".{1,11}",
            message = "Number of characters must be between 1 and 11"
    )
    private String id;

    @NotNull
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?",
            message = "Arrival date time must be in format YYYY-MM-DD'T'hh:mm:ss . For example 2020-07-04T08:12:48."
    )
    private String arrivalDateTime;

    @NotNull
    @Pattern(
            regexp = "[0-9]{1}",
            message = "Storing flag must contains a single number (1-9)"
    )
    private String storingFlag;

    @NotNull
    @Pattern(
            regexp = ".{1,5}",
            message = "Type contains between 1 and 5 characters."
    )
    private String type;

    @NotNull
    @Pattern(
            regexp = ".{1,5}",
            message = "Additional Type contains a single (case insensitive) letter."
    )
    private String additionalType;

    @NotNull
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}(\\.\\d+)?",
            message = "Date of start must be in YYYY-MM-DD format."
    )
    private String acceptanceDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArrivalDateTime() {

        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        java.util.regex.Pattern patternWithoutSeconds =
                java.util.regex.Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(\\.\\d+)?");
        Matcher matcher = patternWithoutSeconds.matcher(arrivalDateTime);
        if (matcher.find()){
            arrivalDateTime = arrivalDateTime + ":00";
        }
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getStoringFlag() {
        return storingFlag;
    }

    public void setStoringFlag(String storingFlag) {
        this.storingFlag = storingFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(String additionalType) {
        this.additionalType = additionalType;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }
}

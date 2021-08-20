package com.apisaes.task.commands.v0;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationCommand {
    private String id;
    private String arrivalDateTime; //DateTimeType
    private String storingFlag;
    private String type;
    private String additionalType;
    private String acceptanceDate; //DateType

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
        Pattern patternWithoutSeconds = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(\\.\\d+)?");
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

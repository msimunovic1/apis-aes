package com.apisaes.task.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;

@Data
public class OperationDTO implements Serializable {

    private static final long serialVersionUID = 1420672609912364077L;

    private String id;
    private XMLGregorianCalendar arrivalDateTime;
    private String storingFlag;
    private String type;
    private String additionalType;
    private XMLGregorianCalendar acceptanceDate;

}

package com.apisaes.task.dataDetails.dto;

import lombok.Data;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DataDTO implements Serializable {

    private static final long serialVersionUID = 1990672609912364060L;

    private OperationDTO operation;
    private OfficeTypeDTO origin;
    private OfficeTypeDTO destination;
    private XMLGregorianCalendar dateOfStart;
    private BigDecimal accountValue;
    private OfficeTypeDTO exporter;
    private List<OfficeTypeDTO> visitingLocations;

}

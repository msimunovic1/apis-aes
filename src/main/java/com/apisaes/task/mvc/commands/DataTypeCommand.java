package com.apisaes.task.mvc.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

package com.apisaes.task.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeTypeCommand {

    @NotNull
    @Pattern(
            regexp = "[A-Z]{2}[A-Z0-9]{6}",
            message = "Reference number must be in regex format '[A-Z]{2}[A-Z0-9]{6}', example: 'AA1AA100'"
    )
    private String referenceNumber;
}

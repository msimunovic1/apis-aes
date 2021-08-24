package com.apisaes.task.commands;

import lombok.*;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDetailsCommand {
    @Valid
    private DataTypeCommand data;
}

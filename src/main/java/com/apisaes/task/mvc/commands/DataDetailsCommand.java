package com.apisaes.task.mvc.commands;

import javax.validation.Valid;

public class DataDetailsCommand {
    @Valid
    private DataTypeCommand data;

    public DataDetailsCommand() {
    }

    public DataTypeCommand getData() {
        return data;
    }

    public void setData(DataTypeCommand data) {
        this.data = data;
    }
}

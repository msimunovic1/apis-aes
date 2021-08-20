package com.apisaes.task.commands.v0;

import javax.validation.constraints.NotNull;

public class DataDetailsCommand {
    @NotNull
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

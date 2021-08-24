package com.apisaes.task.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1420672609912364060L;

    private DataDTO data;
}

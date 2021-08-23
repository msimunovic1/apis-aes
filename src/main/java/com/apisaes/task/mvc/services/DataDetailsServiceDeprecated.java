package com.apisaes.task.mvc.services;

import hr.aaa.test.v0.datadetails.DataDetails;

import java.util.List;

public interface DataDetailsServiceDeprecated {
    List<DataDetails> findAllDataDetails();

    DataDetails findById(String id);

    List<DataDetails> findByOperationId(String id);

    DataDetails saveDataDetails(DataDetails dataDetails);
}
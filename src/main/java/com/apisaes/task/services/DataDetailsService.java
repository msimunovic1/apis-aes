package com.apisaes.task.services;

import hr.aaa.test.v0.datadetails.DataDetails;

import java.util.List;

public interface DataDetailsService {
    List<DataDetails> findAllDataDetails();

    DataDetails findById(String id);

    List<DataDetails> findByOperationId(String id);

    DataDetails saveDataDetails(DataDetails dataDetails);
}
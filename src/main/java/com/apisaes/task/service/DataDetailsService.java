package com.apisaes.task.service;

import hr.aaa.test.v0.datadetails.DataDetails;

import java.util.List;

public interface DataDetailsService {

    DataDetails getDataDetailsById(Long id);
    DataDetails saveDataDetails(DataDetails dataDetails);
    List<DataDetails> getAllDataDetails();
}

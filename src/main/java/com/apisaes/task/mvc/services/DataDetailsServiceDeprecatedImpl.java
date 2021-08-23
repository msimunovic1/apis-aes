package com.apisaes.task.mvc.services;

import com.apisaes.task.mvc.repository.DataDetailsRepositoryDeprecated;
import hr.aaa.test.v0.datadetails.DataDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataDetailsServiceDeprecatedImpl implements DataDetailsServiceDeprecated {

    private final DataDetailsRepositoryDeprecated dataDetailsRepository;

    public DataDetailsServiceDeprecatedImpl(DataDetailsRepositoryDeprecated dataDetailsRepository) {
        this.dataDetailsRepository = dataDetailsRepository;
    }

    @Override
    public List<DataDetails> findAllDataDetails() {
        return dataDetailsRepository.findAllDataDetails();
    }

    @Override
    public DataDetails findById(String id) {
        return dataDetailsRepository.findById(id);
    }

    @Override
    public List<DataDetails> findByOperationId(String id) {
        return dataDetailsRepository.findAllByOperationId(id);
    }

    @Override
    public DataDetails saveDataDetails(DataDetails dataDetails) {
        return dataDetailsRepository.saveDataDetails(dataDetails);
    }
}

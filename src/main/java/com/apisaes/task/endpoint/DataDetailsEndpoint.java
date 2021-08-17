package com.apisaes.task.endpoint;

import com.apisaes.task.repository.DataDetailsRepository;
import hr.aaa.test.v0.datadetails.DataDetailsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DataDetailsEndpoint {

    private static final String NAMESPACE_URI = "http://aaa.hr/test/v0/DataDetails";

    private DataDetailsRepository dataDetailsRepository;

    @Autowired
    public DataDetailsEndpoint(DataDetailsRepository dataDetailsRepository) {
        this.dataDetailsRepository = dataDetailsRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DataDetails")
    @ResponsePayload
    public DataDetailsType getData(@RequestPayload DataDetailsType request) {
        return dataDetailsRepository.findDataDetailsByOperationId(request.getData().getOperation().getID());
    }
}

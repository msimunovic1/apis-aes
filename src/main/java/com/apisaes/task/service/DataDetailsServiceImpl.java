package com.apisaes.task.service;

import com.apisaes.task.exception.NotFoundException;
import com.apisaes.task.dto.DataDetailsDTO;
import com.apisaes.task.model.DataDetailsEntity;
import com.apisaes.task.dto.mapper.DataDetailsMapper;
import com.apisaes.task.repository.DataDetailsRepository;
import com.apisaes.task.validator.SchemaValidator;
import com.apisaes.task.util.Serializer;
import hr.aaa.test.v0.datadetails.DataDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Service
public class DataDetailsServiceImpl implements DataDetailsService {

    private final DataDetailsRepository dataDetailsRepository;
    private final DataDetailsMapper dataDetailsMapper;

    @Override
    public DataDetails getDataDetailsById(Long id) {
        DataDetailsEntity dataDetailsEntity = dataDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data details with id " + id + " not found"));

        DataDetailsDTO dataDetailsDTO = (DataDetailsDTO) Serializer.deserialize(dataDetailsEntity.getDataDetailsBytes());

        return dataDetailsMapper.mapDTOToDataDetails(dataDetailsDTO);
    }

    @Override
    public DataDetails saveDataDetails(DataDetails dataDetails) {
        // validate input fields
        SchemaValidator.validateXmlSchema(dataDetails);

        // transform DataDetails to DataDetailsDTO and serialize to byte[]
        byte[] dataDetailsBytes = Serializer.serialize(dataDetailsMapper.mapDataDetailsToDTO(dataDetails));

        // set data details byte[] to entity
        DataDetailsEntity dataDetailsEntity = new DataDetailsEntity();
        dataDetailsEntity.setDataDetailsBytes(dataDetailsBytes);

        // save data details to DB and deserialize data details byte[] back to DataDetailsDTO
        DataDetailsDTO dataDetailsDTO = (DataDetailsDTO) Serializer.deserialize(dataDetailsRepository.save(dataDetailsEntity).getDataDetailsBytes());

        // transform DataDetailsDTO back to DataDetails
        return dataDetailsMapper.mapDTOToDataDetails(dataDetailsDTO);
    }

    @Override
    public List<DataDetails> getAllDataDetails() {
        List<DataDetailsEntity> dataDetailsEntities = dataDetailsRepository.findAll();
        List<DataDetails> dataDetailsList = new ArrayList<>();

        dataDetailsEntities.forEach(dd ->{
            DataDetailsDTO dataDetailsDTO = (DataDetailsDTO) Serializer.deserialize(dd.getDataDetailsBytes());
            dataDetailsList.add(dataDetailsMapper.mapDTOToDataDetails(dataDetailsDTO));
        });

        return dataDetailsList;
    }

}

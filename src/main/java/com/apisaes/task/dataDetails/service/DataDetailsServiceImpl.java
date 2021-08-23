package com.apisaes.task.dataDetails.service;

import com.apisaes.task.api.exception.NotFoundException;
import com.apisaes.task.dataDetails.dto.DataDetailsDTO;
import com.apisaes.task.dataDetails.entity.DataDetailsEntity;
import com.apisaes.task.dataDetails.mapper.DataDetailsMapper;
import com.apisaes.task.dataDetails.repository.DataDetailsRepository;
import com.apisaes.task.util.SchemaValidation;
import com.apisaes.task.util.Serializer;
import hr.aaa.test.v0.datadetails.DataDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        SchemaValidation.validateXmlSchema(dataDetails);

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

}

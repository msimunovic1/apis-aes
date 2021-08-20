package com.apisaes.task.dataDetails.service;

import com.apisaes.task.dataDetails.dto.DataDetailsDTO;
import com.apisaes.task.dataDetails.entity.DataDetailsEntity;
import com.apisaes.task.dataDetails.mapper.DataDetailsMapper;
import com.apisaes.task.dataDetails.repository.DataDetailsRepository;
import com.apisaes.task.util.Serializer;
import hr.aaa.test.v0.datadetails.DataDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@Service
public class DataDetailsServiceImpl implements DataDetailsService {

    private final DataDetailsRepository dataDetailsRepository;
    private final DataDetailsMapper dataDetailsMapper;

    @Override
    public List<DataDetails> getAllDataDetails() {
        return null;
    }

    @Override
    public DataDetails getDataDetailsById() {
        return null;
    }

    @Override
    public DataDetails saveDataDetails(DataDetails dataDetails) {

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

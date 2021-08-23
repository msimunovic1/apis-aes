package com.apisaes.task.dataDetails.mapper;

import com.apisaes.task.dataDetails.dto.DataDTO;
import com.apisaes.task.dataDetails.dto.DataDetailsDTO;
import com.apisaes.task.dataDetails.dto.OfficeTypeDTO;
import com.apisaes.task.dataDetails.dto.OperationDTO;
import hr.aaa.test.v0.datadetails.DataDetails;
import hr.aaa.test.v0.datadetails.DataType;
import hr.aaa.test.v0.datadetails.OfficeType;
import hr.aaa.test.v0.datadetails.Operation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataDetailsMapper {

    /* JAXB TO DTO */
    public DataDetailsDTO mapDataDetailsToDTO(DataDetails dataDetails) {
        DataDetailsDTO dataDetailsDTO = new DataDetailsDTO();
        dataDetailsDTO.setData(mapDataToDTO(dataDetails.getData()));

        return dataDetailsDTO;
    }

    public DataDTO mapDataToDTO(DataType data) {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setOperation(mapOperationToDTO(data.getOperation()));
        dataDTO.setOrigin(mapOfficeTypeToDTO(data.getOrigin()));
        dataDTO.setDestination(mapOfficeTypeToDTO(data.getDestination()));
        dataDTO.setDateOfStart(data.getDateOfStart());
        dataDTO.setAccountValue(data.getAccountValue());
        dataDTO.setExporter(mapOfficeTypeToDTO(data.getExporter()));
        dataDTO.setVisitingLocations(mapOfficeTypesToDTOs(data.getVisitingLocations()));

        return dataDTO;
    }

    public OperationDTO mapOperationToDTO(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(operation.getID());
        operationDTO.setArrivalDateTime(operation.getArrivalDateTime());
        operationDTO.setStoringFlag(operation.getStoringFlag());
        operationDTO.setType(operation.getType());
        operationDTO.setAdditionalType(operation.getAdditionalType());
        operationDTO.setAcceptanceDate(operation.getAcceptanceDate());

        return operationDTO;
    }

    public OfficeTypeDTO mapOfficeTypeToDTO(OfficeType officeType) {
        OfficeTypeDTO officeTypeDTO = new OfficeTypeDTO();
        officeTypeDTO.setReferenceNumber(officeType.getReferenceNumber());

        return officeTypeDTO;
    }

    public List<OfficeTypeDTO> mapOfficeTypesToDTOs(List<OfficeType> officeTypes) {
        return officeTypes.stream()
                .map(this::mapOfficeTypeToDTO)
                .collect(Collectors.toList());
    }

    /* DTO TO JAXB */
    public DataDetails mapDTOToDataDetails(DataDetailsDTO dataDetailsDTO) {
        DataDetails dataDetails = new DataDetails();
        dataDetails.setData(mapDTOToData(dataDetailsDTO.getData()));

        return dataDetails;
    }

    public DataType mapDTOToData(DataDTO dataDTO) {
        DataType data = new DataType();
        data.setOperation(mapDTOToOperation(dataDTO.getOperation()));
        data.setOrigin(mapDTOToOfficeType(dataDTO.getOrigin()));
        data.setDestination(mapDTOToOfficeType(dataDTO.getDestination()));
        data.setDateOfStart(dataDTO.getDateOfStart());
        data.setAccountValue(dataDTO.getAccountValue());
        data.setExporter(mapDTOToOfficeType(dataDTO.getExporter()));
        dataDTO.getVisitingLocations().forEach(officeTypeDTO -> data.getVisitingLocations().add(mapDTOToOfficeType(officeTypeDTO)));

        return data;
    }

    public Operation mapDTOToOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        operation.setID(operationDTO.getId());
        operation.setArrivalDateTime(operationDTO.getArrivalDateTime());
        operation.setStoringFlag(operationDTO.getStoringFlag());
        operation.setType(operationDTO.getType());
        operation.setAdditionalType(operationDTO.getAdditionalType());
        operation.setAcceptanceDate(operationDTO.getAcceptanceDate());

        return operation;
    }

    public OfficeType mapDTOToOfficeType(OfficeTypeDTO officeTypeDTO) {
        OfficeType officeType = new OfficeType();
        officeType.setReferenceNumber(officeTypeDTO.getReferenceNumber());

        return officeType;
    }

}

package com.apisaes.task.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "data_details")
public class DataDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataDetailsSeq")
    @SequenceGenerator(name = "dataDetailsSeq", sequenceName = "data_details_seq", allocationSize = 1)
    private Long id;

    @Lob
    private byte[] dataDetailsBytes;
}

package com.apisaes.task.dataDetails.repository;

import com.apisaes.task.dataDetails.entity.DataDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DataDetailsRepository extends JpaRepository<DataDetailsEntity, Long> {

}

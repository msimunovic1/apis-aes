package com.apisaes.task.repository;

import com.apisaes.task.model.DataDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface DataDetailsRepository extends JpaRepository<DataDetailsEntity, Long> {

}

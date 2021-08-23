package com.apisaes.task.api.controller;

import com.apisaes.task.dataDetails.service.DataDetailsService;
import hr.aaa.test.v0.datadetails.DataDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dataDetails")
public class DataDetailsController {

    private final DataDetailsService dataDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity<DataDetails> getDataDetailsById(@PathVariable Long id) {
        return new ResponseEntity<>(dataDetailsService.getDataDetailsById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<DataDetails> saveDataDetails(@RequestBody DataDetails dataDetails) {
        return new ResponseEntity<>(dataDetailsService.saveDataDetails(dataDetails), HttpStatus.CREATED);
    }
}

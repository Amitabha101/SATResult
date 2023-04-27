package com.precize.io.app.controllers;

import com.precize.io.app.dtos.InsertDataDTO;
import com.precize.io.app.dtos.UpdateDataDTO;
import com.precize.io.app.models.SATResult;
import com.precize.io.app.services.SATResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SATResultController {

    @Autowired
    private SATResultService service;

    @PostMapping("/insert")
    public ResponseEntity<SATResult> insertData(@RequestBody InsertDataDTO insertDataDTO) {
        SATResult satResult=service.insertData(insertDataDTO);
        return new ResponseEntity<>(satResult, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SATResult>> getAllData() {
        List<SATResult> satResultList= service.getAllData();
        return new ResponseEntity<>(satResultList,HttpStatus.OK);
    }

    @GetMapping("/getRank/{name}")
    public ResponseEntity<Integer> getRank(@PathVariable String name) {
        int rank= service.getRank(name);
        return new ResponseEntity<>(rank,HttpStatus.OK);
    }

    @PutMapping("/updateScore")
    public ResponseEntity<SATResult> updateScore(@RequestBody UpdateDataDTO updateDataDTO) {
        SATResult satResult=service.updateScore(updateDataDTO.getName(), updateDataDTO.getSatScore());
        return new ResponseEntity<>(satResult,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteRecord(@PathVariable String name) {
        service.deleteRecord(name);
    }
}

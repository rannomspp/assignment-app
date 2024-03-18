package com.example.demo.controller;

import com.example.demo.dto.Sector;
import com.example.demo.dto.SectorFieldRow;
import com.example.demo.service.SectorFieldsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorFieldsController {
    private static final Logger logger = LoggerFactory.getLogger(SectorFieldsController.class);
    @Autowired
    SectorFieldsService sectorFieldsService;

    @PostMapping("save")
    public void saveSectorFieldValues(@RequestBody Sector sectorValue) {
        sectorFieldsService.saveSectorFieldValues(sectorValue);
    }

    @PutMapping("update")
    public void updateSectorFieldValues(@RequestBody Sector sectorValue) {
        sectorFieldsService.updateSectorFieldValues(sectorValue);
    }

    @GetMapping("all")
    public ResponseEntity<List<SectorFieldRow>> getSectorFields() {
        return new ResponseEntity<>(sectorFieldsService.getAllSectorFields(), HttpStatus.OK);
    }

}

package com.example.demo.service;

import com.example.demo.controller.SectorFieldsController;
import com.example.demo.dao.SectorFieldsRepository;
import com.example.demo.dao.SectorsRepository;
import com.example.demo.dto.Sector;
import com.example.demo.model.SectorField;
import com.example.demo.dto.SectorFieldRow;
import com.example.demo.model.Sectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SectorFieldsService {

    private static final Logger logger = LoggerFactory.getLogger(SectorFieldsService.class);

    private final SectorFieldsRepository sectorFieldsRepository;
    private final SectorsRepository sectorsRepository;

    @Autowired
    public SectorFieldsService(SectorFieldsRepository sectorFieldsRepository, SectorsRepository sectorsRepository) {
        this.sectorFieldsRepository = sectorFieldsRepository;
        this.sectorsRepository = sectorsRepository;
    }
    public List<SectorFieldRow> getAllSectorFields() {
        List<SectorField> entities = sectorFieldsRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SectorFieldRow convertToDto(SectorField entity) {
        SectorFieldRow dto = new SectorFieldRow();
        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        dto.setValue(entity.getValue());
        return dto;
    }

    @Transactional
    public void saveSectorFieldValues(Sector sectorValue) {
        Sectors sector = new Sectors();
        List<SectorField> listSectorFields = sectorFieldsRepository.findSectorFieldsByIds(sectorValue.getSectors());
        sector.setName(sectorValue.getName());
        sector.setSectors(listSectorFields);
        sector.setAgree(sectorValue.getAgree());
        sectorsRepository.save(sector);
    }

    public void updateSectorFieldValues(Sector sectorValue) {
        List<SectorField> listSectorFields = sectorFieldsRepository.findSectorFieldsByIds(sectorValue.getSectors());
        Optional<Sectors> existingSectorOptional = sectorsRepository.findLastEntityById();
        if (existingSectorOptional.isPresent()) {
            Sectors existingSector = existingSectorOptional.get();
            existingSector.setName(sectorValue.getName());
            existingSector.setSectors(listSectorFields);
            existingSector.setAgree(sectorValue.getAgree());
            sectorsRepository.save(existingSector);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no-entity-to-update");
        }
    }

}

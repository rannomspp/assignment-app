package com.example.demo.dao;

import com.example.demo.model.SectorField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorFieldsRepository extends JpaRepository<SectorField, Long> {

    @Query(value = "SELECT sf FROM SectorField sf WHERE sf.value IN :ids")
    List<SectorField> findSectorFieldsByIds(@Param("ids") List<Integer> ids);

}
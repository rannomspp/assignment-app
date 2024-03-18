package com.example.demo.dao;

import com.example.demo.model.Sectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectorsRepository extends JpaRepository<Sectors, Long> {

    @Query(value = "SELECT e FROM Sectors e WHERE e.id = (SELECT MAX(e2.id) FROM Sectors e2)")
    Optional<Sectors> findLastEntityById();

}

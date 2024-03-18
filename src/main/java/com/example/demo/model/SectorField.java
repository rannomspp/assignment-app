package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "sectorfields")
public class SectorField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("label")
    private String label;

    @JsonProperty("value")
    private Integer value;

}

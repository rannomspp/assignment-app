package com.example.demo.model;

import com.example.demo.dto.SectorFieldRow;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.List;

@Entity
@Data
@Table(name = "sectors")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Sectors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @JsonProperty("sectors")
    private List<SectorField> sectors;

    @JsonProperty("agree")
    private boolean agree;

}

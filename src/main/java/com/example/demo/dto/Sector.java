package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Sector {
    private String name;
    private List<Integer> sectors;
    private Boolean agree;
}

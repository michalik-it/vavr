package it.michalik.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private int id;
    private int height;
    private String name;
    private String cv;
}
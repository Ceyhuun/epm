package com.company.sintra.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link com.company.sintra.entity.ProductEntity} entity
 */
@Data
public class ProductEntityDto implements Serializable {
    private  int id;
    private  String name;
    private  int count;
}
package com.company.sintra.dto;

import com.company.sintra.entity.ProductEntity;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link com.company.sintra.entity.DateProductsEntity} entity
 */
@Data
public class DateProductsEntityDto implements Serializable {
    private int productsId;
    private Integer count;
    private Date date;
}
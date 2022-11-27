package com.company.sintra.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link com.company.sintra.entity.EmployeesProductsEntity} entity
 */
@Data
public class EmployeesProductsEntityDto implements Serializable {
    private int id;
    private int employeesId;
    private int productId;
    private Date date;
    private int productCount;
}
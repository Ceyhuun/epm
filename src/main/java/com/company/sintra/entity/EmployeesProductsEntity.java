package com.company.sintra.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmployeesProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    private int employeesId;
    @Basic
    private int productId;
    @Basic
    private Date date;
    @Basic
    private int productCount;

}

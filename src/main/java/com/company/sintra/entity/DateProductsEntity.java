package com.company.sintra.entity;

import com.company.sintra.dto.ProductEntityDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DateProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    private int productsId;
    @Basic
    private Integer count;
    @Basic
    private Date date;


}

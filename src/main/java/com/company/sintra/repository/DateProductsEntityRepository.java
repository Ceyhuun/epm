package com.company.sintra.repository;

import com.company.sintra.entity.DateProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateProductsEntityRepository extends JpaRepository<DateProductsEntity, Integer> {
    DateProductsEntity findById(int id);
}
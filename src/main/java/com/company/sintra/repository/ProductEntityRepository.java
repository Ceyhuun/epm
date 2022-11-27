package com.company.sintra.repository;

import com.company.sintra.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findById(int id);
}
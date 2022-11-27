package com.company.sintra.repository;

import com.company.sintra.entity.EmployeesProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesProductsEntityRepository extends JpaRepository<EmployeesProductsEntity, Integer> {
    EmployeesProductsEntity findById(int id);
}
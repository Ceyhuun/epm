package com.company.sintra.repository;

import com.company.sintra.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesEntityRepository extends JpaRepository<EmployeesEntity, Integer> {

    EmployeesEntity findById(int id);
}
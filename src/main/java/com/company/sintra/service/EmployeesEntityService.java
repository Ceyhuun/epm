package com.company.sintra.service;

import com.company.sintra.dto.EmployeesEntityDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeesEntityService extends CrudService<EmployeesEntityDto, Integer> {
}

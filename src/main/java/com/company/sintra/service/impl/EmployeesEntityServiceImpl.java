package com.company.sintra.service.impl;

import com.company.sintra.dto.EmployeesEntityDto;
import com.company.sintra.entity.EmployeesEntity;
import com.company.sintra.repository.EmployeesEntityRepository;
import com.company.sintra.service.EmployeesEntityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeesEntityServiceImpl implements EmployeesEntityService {
    private final EmployeesEntityRepository employeesEntityRepository;
    private final ModelMapper modelMapper;
    @Override
    public void create(EmployeesEntityDto employeesEntityDto) {
        EmployeesEntity employeesEntity= EmployeesEntity.builder()
                .name(employeesEntityDto.getName())
                .build();
        employeesEntityRepository.save(employeesEntity);
    }

    @Override
    public List<EmployeesEntityDto> getAll() {
        List<EmployeesEntity > employeesEntityList= employeesEntityRepository.findAll();
        return employeesEntityList.stream().map(employeesEntity -> modelMapper.map(employeesEntity, EmployeesEntityDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void update(Integer idd, EmployeesEntityDto employeesEntityDto) {
        EmployeesEntity employeesEntity= employeesEntityRepository.findById(idd.intValue());
        employeesEntity.setName(employeesEntityDto.getName());
        employeesEntityRepository.save(employeesEntity);

    }

    @Override
    public void deleteById(Integer idd) {
        employeesEntityRepository.deleteById(idd);

    }

    @Override
    public EmployeesEntityDto findById(Integer idd) {
        EmployeesEntity employeesEntity = employeesEntityRepository.findById(idd.intValue());
        return modelMapper.map(employeesEntity, EmployeesEntityDto.class);

    }
}

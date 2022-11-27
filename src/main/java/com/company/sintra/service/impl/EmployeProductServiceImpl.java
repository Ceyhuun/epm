package com.company.sintra.service.impl;

import com.company.sintra.dto.EmployeesProductsEntityDto;
import com.company.sintra.entity.EmployeesProductsEntity;
import com.company.sintra.entity.ProductEntity;
import com.company.sintra.repository.EmployeesProductsEntityRepository;
import com.company.sintra.repository.ProductEntityRepository;
import com.company.sintra.service.EmployeesProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeProductServiceImpl implements EmployeesProductService {

    private final EmployeesProductsEntityRepository employeesProductsEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(EmployeesProductsEntityDto employeesProductsEntityDto) {
        EmployeesProductsEntity entity = EmployeesProductsEntity
                .builder()
                .employeesId(employeesProductsEntityDto.getEmployeesId())
                .productId(employeesProductsEntityDto.getProductId())
                .date(employeesProductsEntityDto.getDate())
                .productCount(employeesProductsEntityDto.getProductCount())
                .build();

        ProductEntity productEntity = productEntityRepository.findById(employeesProductsEntityDto.getProductId());
        int result = productEntity.getCount() - employeesProductsEntityDto.getProductCount();
        productEntity.setCount(result);
        productEntityRepository.save(productEntity);
        employeesProductsEntityRepository.save(entity);
    }

    @Override
    public List<EmployeesProductsEntityDto> getAll() {
        List<EmployeesProductsEntity> employeesEntityList = employeesProductsEntityRepository.findAll();
        return employeesEntityList.stream().map(employeesProductsEntity -> modelMapper.map(employeesProductsEntity
                , EmployeesProductsEntityDto.class)).collect(Collectors.toList());
    }

    @Override
    public void update(Integer idd, EmployeesProductsEntityDto employeesProductsEntityDto) {

    }

    @Override
    public void deleteById(Integer idd) {
        EmployeesProductsEntity entity = employeesProductsEntityRepository.findById(idd.intValue());
        ProductEntity productEntity = productEntityRepository.findById(entity.getProductId());
        int result = entity.getProductCount() + productEntity.getCount();
        productEntity.setCount(result);
        productEntityRepository.save(productEntity);
        employeesProductsEntityRepository.deleteById(idd);
    }

    @Override
    public EmployeesProductsEntityDto findById(Integer idd) {
        EmployeesProductsEntity entity = employeesProductsEntityRepository.findById(idd.intValue());
        return modelMapper.map(entity, EmployeesProductsEntityDto.class);
    }
}

package com.company.sintra.service;

import com.company.sintra.dto.ProductEntityDto;
import com.company.sintra.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductEntityService extends CrudService<ProductEntityDto,Integer> {
}

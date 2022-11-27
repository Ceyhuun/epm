package com.company.sintra.service;

import com.company.sintra.dto.DateProductsEntityDto;
import com.company.sintra.entity.DateProductsEntity;
import org.springframework.stereotype.Service;

@Service
public interface DateProductsEntityService extends CrudService<DateProductsEntityDto, Integer> {
}

package com.company.sintra.controller;

import com.company.sintra.dto.DateProductsEntityDto;
import com.company.sintra.service.DateProductsEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class DateProductEntityController {
    private final DateProductsEntityService dateProductsEntityService;

    @PostMapping
    public void create(@RequestBody DateProductsEntityDto dateProductsEntityDto) {
        dateProductsEntityService.create(dateProductsEntityDto);
    }

    @GetMapping
    public ResponseEntity<List<DateProductsEntityDto>> getAll() {
        List<DateProductsEntityDto> list = dateProductsEntityService.getAll();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody DateProductsEntityDto dateProductsEntityDto) {
        dateProductsEntityService.update(id, dateProductsEntityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        dateProductsEntityService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateProductsEntityDto> findById(@PathVariable Integer id) {
        DateProductsEntityDto dateProductsEntityDto = dateProductsEntityService.findById(id);
        return ResponseEntity.ok(dateProductsEntityDto);
    }
}

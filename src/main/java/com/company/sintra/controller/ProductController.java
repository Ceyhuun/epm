package com.company.sintra.controller;

import com.company.sintra.dto.ProductEntityDto;
import com.company.sintra.service.ProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/list")
public class ProductController {
    private final ProductEntityService productEntityService;

    @PostMapping
    public void create(@RequestBody ProductEntityDto productEntityDto) {
        productEntityService.create(productEntityDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntityDto>> getAll() {
        List<ProductEntityDto> productEntityDtos = productEntityService.getAll();
        return ResponseEntity.ok(productEntityDtos);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ProductEntityDto productEntityDto) {
        productEntityService.update(id, productEntityDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        productEntityService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntityDto> findById(@PathVariable Integer id) {
        ProductEntityDto productEntityDto = productEntityService.findById(id);
        return ResponseEntity.ok(productEntityDto);
    }
}

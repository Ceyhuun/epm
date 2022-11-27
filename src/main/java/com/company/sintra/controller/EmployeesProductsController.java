package com.company.sintra.controller;

import com.company.sintra.dto.EmployeesProductsEntityDto;
import com.company.sintra.service.EmployeesProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees/products")
public class EmployeesProductsController {
    private final EmployeesProductService employeesProductService;

    @GetMapping
    public ResponseEntity<List<EmployeesProductsEntityDto>> getAll() {
        List<EmployeesProductsEntityDto> entity = employeesProductService.getAll();
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public void create(@RequestBody EmployeesProductsEntityDto entity) {
        employeesProductService.create(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employeesProductService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeesProductsEntityDto> findById(@PathVariable Integer id) {
        EmployeesProductsEntityDto employeesProductsEntityDto = employeesProductService.findById(id);
        return ResponseEntity.ok(employeesProductsEntityDto);
    }
}

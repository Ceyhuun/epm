package com.company.sintra.controller;

import com.company.sintra.dto.EmployeesEntityDto;
import com.company.sintra.service.EmployeesEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeesEntityController {
    private final EmployeesEntityService employeesEntityService;

    @PostMapping()
    public  void create(@RequestBody EmployeesEntityDto employeesEntityDto){
        employeesEntityService.create(employeesEntityDto);
    }

    @GetMapping("/get")
    public ResponseEntity<List<EmployeesEntityDto>>  getAll(){
        List <EmployeesEntityDto> employeesEntityDtos= employeesEntityService.getAll();
        return ResponseEntity.ok(employeesEntityDtos);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Integer id , @RequestBody EmployeesEntityDto employeesEntityDto){
        employeesEntityService.update(id, employeesEntityDto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<EmployeesEntityDto > findById(@PathVariable  Integer id){
        EmployeesEntityDto employeesEntityDto= employeesEntityService.findById(id);
        return ResponseEntity.ok(employeesEntityDto);

    }

    @DeleteMapping("/{id}")
    public  void  deleteById (@PathVariable Integer id){
        employeesEntityService.deleteById(id);
    }

}

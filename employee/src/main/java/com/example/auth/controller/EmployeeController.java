package com.example.auth.controller;

import com.example.auth.dto.EmployeeDto;
import com.example.auth.exception.DeleteEmployeeException;
import com.example.auth.exception.FindEmployeeException;
import com.example.auth.exception.UpdateEmployeeException;
import com.example.auth.response.EmployeeResponse;
import com.example.auth.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createEmploye")
    public EmployeeDto create (@RequestBody EmployeeDto employeeDto){
        return employeeService.create(employeeDto);
    }

    @GetMapping("/find/{id}")
    public EmployeeResponse findEmployee(@PathVariable (value = "id") long id) throws FindEmployeeException {
        return employeeService.findEmployee(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable (value = "id") long id) throws DeleteEmployeeException {
        return employeeService.deleteEmployee(id);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable (value = "id")long id,@RequestBody EmployeeDto employeeDto ) throws UpdateEmployeeException {
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeDto).getBody());
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeDto> getAllEmployee(){
        List<EmployeeDto> employeeDtoList=employeeService.getAllEmployee();
        return employeeDtoList;
    }
}

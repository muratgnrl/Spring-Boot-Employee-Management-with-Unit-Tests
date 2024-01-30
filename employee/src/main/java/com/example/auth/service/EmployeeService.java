package com.example.auth.service;

import com.example.auth.dto.EmployeeDto;
import com.example.auth.exception.DeleteEmployeeException;
import com.example.auth.exception.FindEmployeeException;
import com.example.auth.exception.UpdateEmployeeException;
import com.example.auth.model.EmployeeEntity;
import com.example.auth.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {
     EmployeeDto create(EmployeeDto employeeDto);


     EmployeeDto entityToDto(EmployeeEntity entity);

   EmployeeEntity dtoToEntity(EmployeeDto employeeDto);


    EmployeeResponse findEmployee(long id) throws FindEmployeeException;

    ResponseEntity<EmployeeResponse> deleteEmployee(long id) throws DeleteEmployeeException;

    ResponseEntity<EmployeeDto> updateEmployee(long id, EmployeeDto employeeDto) throws UpdateEmployeeException;

    List<EmployeeDto> getAllEmployee();
}

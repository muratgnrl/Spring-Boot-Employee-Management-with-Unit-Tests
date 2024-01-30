package com.example.auth.service.impl;

import com.example.auth.dto.EmployeeDto;
import com.example.auth.exception.DeleteEmployeeException;
import com.example.auth.exception.FindEmployeeException;
import com.example.auth.exception.UpdateEmployeeException;
import com.example.auth.model.EmployeeEntity;
import com.example.auth.repository.EmployeeRepository;
import com.example.auth.response.EmployeeResponse;
import com.example.auth.response.Meta;
import com.example.auth.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity entity=dtoToEntity(employeeDto);
        employeeRepository.save(entity);
        if (entity==null){
            throw new RuntimeException("Kayıt başarısız");
        }
        return employeeDto;

    }

    @Override
    public EmployeeResponse findEmployee(long id) throws FindEmployeeException {
        EmployeeEntity entity=findByDto(id);
        if (entity!=null){
            EmployeeResponse employeeResponse=new EmployeeResponse();
            employeeResponse.employee=entityToDto(entity);
            employeeResponse.meta=new Meta(200,"Kullanıcı bulundu");
            return employeeResponse;
        } else {
         throw new FindEmployeeException();
        }

    }
    @Override
    public ResponseEntity<EmployeeResponse> deleteEmployee(long id) throws DeleteEmployeeException {
        Optional<EmployeeEntity>optionalEmployee=employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){

            employeeRepository.deleteById(id);
            EmployeeResponse employeeResponse=new EmployeeResponse();
            employeeResponse.meta=new Meta(200,"Silindi  =  " + id);
            return ResponseEntity.ok(employeeResponse);
        }else {
            throw new DeleteEmployeeException();
        }
    }

    private EmployeeEntity findByDto(long id) {
        EmployeeEntity entity=employeeRepository.findEmployeeEntityById(id);
        return entity;
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(long id, EmployeeDto employeeDto) throws UpdateEmployeeException {

        EmployeeEntity employeeEntity=dtoToEntity(employeeDto);
        EmployeeEntity employee=employeeRepository.findEmployeeEntityById(id);

        employee.setLastName(employeeEntity.getLastName());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setEmailId(employeeEntity.getEmailId());

        EmployeeEntity updatedEmployee=employeeRepository.save(employee);
        EmployeeDto entityToDto=entityToDto(updatedEmployee);
        return ResponseEntity.ok(entityToDto);
    }


    @Override
    public EmployeeDto entityToDto(EmployeeEntity entity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(entity.getId());
        employeeDto.setEmailId(entity.getEmailId());
        employeeDto.setFirstName(entity.getFirstName());
        employeeDto.setLastName(entity.getLastName());
        return employeeDto;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setEmailId(employeeDto.getEmailId());
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        return employeeEntity;
    }
    @Override
    public List<EmployeeDto> getAllEmployee(){

        List<EmployeeDto> dtoList=new ArrayList<>();

        Iterable<EmployeeEntity> employeeEntities=employeeRepository.findAll();

        for (EmployeeEntity entity :employeeEntities){
            EmployeeDto employeeDto=entityToDto(entity);
            dtoList.add(employeeDto);
        }
        return dtoList;
    }


}

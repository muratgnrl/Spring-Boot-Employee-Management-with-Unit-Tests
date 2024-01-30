package com.example.auth.repository;


import com.example.auth.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findEmployeeEntityById(long id);
}

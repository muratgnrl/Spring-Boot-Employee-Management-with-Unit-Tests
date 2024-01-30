package com.example.auth.exception;

import com.example.auth.response.EmployeeResponse;
import com.example.auth.response.Meta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> findException(FindEmployeeException findException, WebRequest webRequest){
        EmployeeResponse employeeResponse =new EmployeeResponse();
        Meta meta=new Meta();
        meta.errorCode=4034;
        meta.errorDescription="Kullanıcı bulunamadı ";

        employeeResponse.meta=meta;
        ResponseEntity<Object> entity =new ResponseEntity<>(employeeResponse, HttpStatus.NOT_FOUND);
        return entity;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> deleteException(DeleteEmployeeException deleteEmployeeException, WebRequest webRequest){
        EmployeeResponse employeeResponse =new EmployeeResponse();
        Meta meta=new Meta();
        meta.errorCode=4020;
        meta.errorDescription="Kullanıcı Bulunamadığı için silinemedi ";

        employeeResponse.meta=meta;
        ResponseEntity<Object> entity =new ResponseEntity<>(employeeResponse, HttpStatus.NOT_FOUND);
        return entity;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> updateException(UpdateEmployeeException updateEmployeeException, WebRequest webRequest){
        EmployeeResponse employeeResponse =new EmployeeResponse();
        Meta meta=new Meta();
        meta.errorCode=4010;
        meta.errorDescription="Kullanıcı Güncellenemedi ";

        employeeResponse.meta=meta;
        ResponseEntity<Object> entity =new ResponseEntity<>(employeeResponse, HttpStatus.NOT_FOUND);
        return entity;
    }


}

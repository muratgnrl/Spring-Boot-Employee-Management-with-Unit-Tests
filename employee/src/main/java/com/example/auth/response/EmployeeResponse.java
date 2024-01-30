package com.example.auth.response;

import com.example.auth.dto.EmployeeDto;


import java.io.Serializable;

public class EmployeeResponse extends BaseResponse implements Serializable {
    public EmployeeDto employee;
}

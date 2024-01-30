package com.example.auth.response;

import com.example.auth.model.BaseEntity;

import java.io.Serializable;

public class Meta  extends BaseEntity implements Serializable  {
    public int errorCode;
    public String errorDescription;

    public Meta(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public Meta() {
    }
}

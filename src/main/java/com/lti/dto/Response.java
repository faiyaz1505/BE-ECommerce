package com.lti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {
    private T data;
    private String success;
    private int successCode;
    private String message;
}

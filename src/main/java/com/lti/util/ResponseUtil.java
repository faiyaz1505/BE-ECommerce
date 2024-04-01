package com.lti.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lti.dto.Response;

public class ResponseUtil {

    public static  <T> ResponseEntity<Object> getResponse(T data, HttpStatus status, String message, String success){
        Response<T> response=new Response<T>(data,success,status.value(),message);
        return new ResponseEntity<Object>(response,new HttpHeaders(),status);
    }
}

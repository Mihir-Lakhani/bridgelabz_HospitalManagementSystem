package com.example.HospitalManagementSystem.exception;

import java.util.*;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }
}
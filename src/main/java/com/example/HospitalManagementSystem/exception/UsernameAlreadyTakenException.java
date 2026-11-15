package com.example.HospitalManagementSystem.exception;

import java.util.*;


public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message){
        super(message);
    }

}
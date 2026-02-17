package com.project.EMS.exception;

public class EmployeeNotActiveException extends RuntimeException{
    public EmployeeNotActiveException(String message){
        super(message);
    }
}

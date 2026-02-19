package com.project.EMS.exception;

public class InvalidSalaryAmountException extends RuntimeException{
    public InvalidSalaryAmountException(String message){
        super(message);
    }
}

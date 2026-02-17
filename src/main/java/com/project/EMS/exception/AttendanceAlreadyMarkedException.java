package com.project.EMS.exception;


public class AttendanceAlreadyMarkedException extends RuntimeException{
    public AttendanceAlreadyMarkedException (String message){
        super(message);
    }
}

package com.project.EMS.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public Map<String, Object> handleErrorMessage(Exception ex, HttpServletRequest request){

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status",404);
        body.put("error", "NOT_FOUND");
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        return body;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ){
       Map<String,Object> body =  handleErrorMessage(ex, request);


        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendanceAlreadyMarkedException.class)
    public ResponseEntity<?> handleAttendanceAlreadyMarkedException(AttendanceAlreadyMarkedException ex,
                                                                    HttpServletRequest request){
        Map<String,Object> body =  handleErrorMessage(ex, request);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmployeeNotActiveException.class)
    public ResponseEntity<?> handleEmployeeNotActiveException(EmployeeNotActiveException ex,
                                                                    HttpServletRequest request){
        Map<String,Object> body =  handleErrorMessage(ex, request);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(
            Exception ex,
            HttpServletRequest request) {

        Map<String,Object> body =  handleErrorMessage(ex, request);


        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

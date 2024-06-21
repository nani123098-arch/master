package com.naniguides.springboot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExpectionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> userResourceNotFound(ResourceNotFoundException exp,
                                                             WebRequest webRequest)
    {
        ErrorDetails err = new ErrorDetails(
                LocalDateTime.now(),
                exp.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"


        );
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorDetails> emailResourceNotFound(EmailExistsException exp,
                                                              WebRequest webRequest)
    {
        ErrorDetails err = new ErrorDetails(
                LocalDateTime.now(),
                exp.getMessage(),
                webRequest.getDescription(false),
                "Email_Already_Exists"


        );
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception exp ,
                                                               WebRequest webRequest)
    {
        ErrorDetails err = new ErrorDetails(
                LocalDateTime.now(),
                exp.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL ISSUE ON YOUR CODE"

        );

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                }

                );

        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }
}

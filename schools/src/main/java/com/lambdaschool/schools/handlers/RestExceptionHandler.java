package com.lambdaschool.schools.handlers;


import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetails;
import com.lambdaschool.schools.models.ValidationError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



   public static List<ValidationError> getConstraintViolations(Throwable cause){

         List<ValidationError> validationErrors = new ArrayList<>();


         while(cause != null){


            if(cause instanceof ConstraintViolationException) {
               ConstraintViolationException exception = (ConstraintViolationException) cause;
               ValidationError validationError = new ValidationError();
               validationError.setFieldname(exception.toString());
               validationError.setMessage(exception.getMessage());

               validationErrors.add(validationError);


            }

            if ( cause instanceof MethodArgumentNotValidException){
               MethodArgumentNotValidException exception = (MethodArgumentNotValidException) cause;
               List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

               for (FieldError fe: fieldErrors){
                  ValidationError validationError = new ValidationError();

                  validationError.setFieldname(fe.getField());
                  validationError.setMessage(fe.getDefaultMessage());

                  validationErrors.add(validationError);

               }


            }
            cause = cause.getCause();
         }

         return validationErrors;
      }



   @ExceptionHandler
   protected ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
      ErrorDetails errorDetail = new ErrorDetails();

      errorDetail.setTitle("Resource Not Found");
      errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
      errorDetail.setDetail(rnfe.getMessage());
      errorDetail.setDeveloperMessage(rnfe.getClass().getName());
      errorDetail.setTimestamp(new Date());
      errorDetail.setErrors(getConstraintViolations(rnfe));


      return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
   }


   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

      ErrorDetails errorDetail = new ErrorDetails();

      errorDetail.setTitle("Rest Internal Exception");
      errorDetail.setStatus(status.value());
      errorDetail.setDetail("Found an issue with School: "+ex.getMessage());
      errorDetail.setDeveloperMessage(ex.getClass().getName());
      errorDetail.setTimestamp(new Date());
      errorDetail.setErrors(getConstraintViolations(ex));


      return new ResponseEntity<>(errorDetail, headers, status);


   }


   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(
           NoHandlerFoundException ex,
           HttpHeaders headers,
           HttpStatus status,
           WebRequest request)
   {
      ErrorDetails errorDetail = new ErrorDetails();
      errorDetail.setTimestamp(new Date());
      errorDetail.setStatus(status.value());
      errorDetail.setTitle("Rest Endpoint Not Valid");
      errorDetail.setDetail("Found an issue with School: "+ex.getMessage());
      errorDetail.setDeveloperMessage(ex.getClass().getName());
      errorDetail.setErrors(getConstraintViolations(ex));

      return new ResponseEntity<>(errorDetail,
              null,
              status);
   }



}

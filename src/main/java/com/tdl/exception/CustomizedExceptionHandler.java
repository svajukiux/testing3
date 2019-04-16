package com.tdl.exception;

import java.util.Date;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component
//@ControllerAdvice
//public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	//@ExceptionHandler(Exception.class)
	//public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request){
	//	ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
	//	return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	//}
	
	//@ExceptionHandler(ToDoNoteNotFoundException.class)
	//public final ResponseEntity<ErrorDetails> handleNoteNotFoundException(ToDoNoteNotFoundException ex, WebRequest request){
		
//		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
	//	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	//}
	
	
	//@ExceptionHandler(HttpMessageNotReadableException.class)
	//@Override
	//protected final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	//	ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
	//	errorDetails.setMessage("Testing message");
	//	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	//}
	
	//(InvalidFormatException.class)
	//public final ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	//	ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),request.getDescription(true));
	//	errorDetails.setMessage("Testing message");
	//	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
		
//	}
	
    
	
	//@Override
	 //  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	  //     String error = "Malformed JSON request";
	   //    ErrorDetails = newErrorDetails
	    //   return buildResponseEntity(new ErrorDetails(,HttpStatus.BAD_REQUEST));
	   //}
	
//}

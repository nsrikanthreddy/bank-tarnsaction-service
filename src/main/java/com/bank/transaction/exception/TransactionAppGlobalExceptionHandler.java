package com.bank.transaction.exception;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.transaction.payload.ErrorInfo;

@RestControllerAdvice
public class TransactionAppGlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ErrorInfo> handleResourceNotFoundException(TransactionNotFoundException exception,
			WebRequest webRequest) {

		ErrorInfo errordetails = new ErrorInfo(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = {InsufficientFundException.class, InvalidDepositAmountException.class})
	public ResponseEntity<ErrorInfo> handleAmountException(Exception exception,
			WebRequest webRequest) {

		ErrorInfo errordetails = new ErrorInfo(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleApplicationException(Exception exception,
			WebRequest webRequest) {

		ErrorInfo errordetails = new ErrorInfo(new Date(), "Application Error : "+exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

package com.bank.transaction.exception;

public class InvalidDepositAmountException extends RuntimeException {

	public InvalidDepositAmountException(String message) {
		super(message);
	}
}

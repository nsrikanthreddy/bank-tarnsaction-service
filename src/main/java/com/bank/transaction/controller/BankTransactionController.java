package com.bank.transaction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transaction.enums.TransactionType;
import com.bank.transaction.exception.InvalidDepositAmountException;
import com.bank.transaction.payload.AccountTransactionDto;
import com.bank.transaction.service.AccountTransactionService;


@RestController
@RequestMapping("/api/bank/transactions")
public class BankTransactionController {

	Logger logger= LoggerFactory.getLogger(BankTransactionController.class);

	@Autowired
	private AccountTransactionService accountTransactionService;
	
	
	@PostMapping("/withdraAmount")
	public ResponseEntity<AccountTransactionDto> withdraAmount(@RequestBody AccountTransactionDto accountTransactionDto) {
		
		if(accountTransactionDto.getTransactionType() != TransactionType.DEBIT) {
			logger.error(" Invalid deposit amount");
			throw new RuntimeException("Invalid transaction type for withdraw");
		}
		logger.info("making transaction by withdraw amount from "+accountTransactionDto.getAccountId()+" account");
		
		return new ResponseEntity<>(accountTransactionService.doBankAccountTransaction(accountTransactionDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/depositAmount")
	public ResponseEntity<AccountTransactionDto> depositAmount(@RequestBody AccountTransactionDto accountTransactionDto) {
		
		if(accountTransactionDto.getTransactionType() != TransactionType.CREDIT) {
			logger.error(" Invalid deposit amount");
			throw new RuntimeException("Invalid transaction type for deposit");
		}
		
		logger.info("making transaction by deposit amount in "+accountTransactionDto.getAccountId()+" account");
		
		return new ResponseEntity<>(accountTransactionService.doBankAccountTransaction(accountTransactionDto), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getTransactionsFor/{accountId}/Account")
	public ResponseEntity<List<AccountTransactionDto>> getUserById(@PathVariable(name = "accountId") Long accountId) {
		
		logger.info("finding Transactions by accountId "+accountId);
		
		return ResponseEntity.ok(accountTransactionService.getAccountTransactionsByAccountId(accountId));
	}
}

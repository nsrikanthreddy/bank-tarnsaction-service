package com.bank.transaction.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.transaction.dao.AccountTransactionRepository;
import com.bank.transaction.entity.AccountTransaction;
import com.bank.transaction.enums.TransactionType;
import com.bank.transaction.exception.InsufficientFundException;
import com.bank.transaction.exception.InvalidDepositAmountException;
import com.bank.transaction.exception.TransactionNotFoundException;
import com.bank.transaction.extrenal.service.communication.BankAccountService;
import com.bank.transaction.payload.AccountTransactionDto;
import com.bank.transaction.payload.BankAccountDto;
import com.bank.transaction.service.AccountTransactionService;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

	Logger logger= LoggerFactory.getLogger(AccountTransactionServiceImpl.class);
	
	@Autowired
	private AccountTransactionRepository accountTransactionRepository;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public AccountTransactionDto doBankAccountTransaction(AccountTransactionDto accountTransactionDto) {
		
		// get Bank account and get current balance
		BankAccountDto bankAccountDto= bankAccountService.getBankAccountById(accountTransactionDto.getAccountId());
		if(accountTransactionDto.getTransactionType()==TransactionType.CREDIT) {
			if(accountTransactionDto.getTransactionAmount() <= 0) {
				logger.error(" Invalid deposit amount");
				throw new InvalidDepositAmountException("Invalid deposit amount");
			}
			bankAccountDto.setCurrentBalance(bankAccountDto.getCurrentBalance() + accountTransactionDto.getTransactionAmount());
		}
		
        if(accountTransactionDto.getTransactionType()==TransactionType.DEBIT) {
        	if(accountTransactionDto.getTransactionAmount() > bankAccountDto.getCurrentBalance()) {
        		logger.error(" Insufficient fund to with withdra amount");
				throw new InsufficientFundException("Insufficient fund to with withdra amount");
			}
        	bankAccountDto.setCurrentBalance(bankAccountDto.getCurrentBalance() - accountTransactionDto.getTransactionAmount());
		}

        accountTransactionDto.setTransactionTime(LocalDateTime.now());
		AccountTransaction transaction = mapToEntity(accountTransactionDto);
		
		//saving transaction
		AccountTransaction savedTransaction = accountTransactionRepository.save(transaction);
		
		// update account current balance by making service call to bank account service
		bankAccountService.updateBankAccount(bankAccountDto, accountTransactionDto.getAccountId());

	    logger.info(accountTransactionDto.getTransactionType().toString()+" transaction saved successfully");
		
		// convert entity to DTO
	    AccountTransactionDto transactionResponse = mapToDto(savedTransaction);

		return transactionResponse;
	}
	
	

	@Override
	public List<AccountTransactionDto> getAccountTransactionsByAccountId(Long accountId) {

		List<AccountTransaction> accountTransactions = accountTransactionRepository.findTransactionsByAccountId(accountId);
		
		if(accountTransactions==null) {
			logger.error("Account "+accountId+" does not have transactions !");
			throw new TransactionNotFoundException("No transactions found for account "+accountId);
		}
		
		logger.info("Account "+accountId+" transactions fetched successfully");
		
		return accountTransactions.stream().map(tx -> mapToDto(tx)).collect(Collectors.toList());
	}

	
   public AccountTransaction mapToEntity(AccountTransactionDto accountTransactionDto) {
		
	   AccountTransaction accountTransaction = modelMapper.map(accountTransactionDto, AccountTransaction.class);

			return accountTransaction;

	}
		
	public AccountTransactionDto mapToDto(AccountTransaction accountTransaction) {

		AccountTransactionDto accountTransactionDto = modelMapper.map(accountTransaction, AccountTransactionDto.class);

			return accountTransactionDto;
	} 
}

package com.bank.transaction.service;

import java.util.List;

import com.bank.transaction.payload.AccountTransactionDto;


public interface AccountTransactionService {

	
    AccountTransactionDto doBankAccountTransaction(AccountTransactionDto accountTransactionDto);
	
//	List<AccountTransactionDto> getAllAccountTransaction();
	
	List<AccountTransactionDto> getAccountTransactionsByAccountId(Long accountId);
	
}

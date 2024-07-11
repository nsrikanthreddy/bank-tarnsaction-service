package com.bank.transaction.extrenal.service.communication;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.transaction.payload.BankAccountDto;

@FeignClient(name = "bank-account-service", url = "http://localhost:8085", path = "/api/bank/accounts")
@Service
public interface BankAccountService {

	@GetMapping(value = "/findBankAccount/{id}")
	public BankAccountDto getBankAccountById(@PathVariable(name = "id") Long id);
	
	@PutMapping(value = "/updateBankAccount/{accountId}")
	public BankAccountDto updateBankAccount(@RequestBody BankAccountDto bankAccountDto, @PathVariable(name = "accountId") Long accountId);
}

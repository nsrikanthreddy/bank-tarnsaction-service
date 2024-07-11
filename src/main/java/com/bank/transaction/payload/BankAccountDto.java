package com.bank.transaction.payload;


import com.bank.transaction.enums.AccountStatus;
import com.bank.transaction.enums.AccountType;
import com.bank.transaction.enums.BusinessType;
import com.bank.transaction.enums.WealthUnit;

import lombok.Data;

@Data
public class BankAccountDto {

private Long accountId;
	
	private Long customerId;
	
	private AccountType accountType;
	
	private Double currentBalance;
	
	private AccountStatus accountStatus;
	
	

    private Double wealth;
	
	private WealthUnit wealthUnit;
	
	
    private BusinessType businessType;
	
	private Double totalRevenue;
	
	private Address officeAddress;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Double getWealth() {
		return wealth;
	}

	public void setWealth(Double wealth) {
		this.wealth = wealth;
	}

	public WealthUnit getWealthUnit() {
		return wealthUnit;
	}

	public void setWealthUnit(WealthUnit wealthUnit) {
		this.wealthUnit = wealthUnit;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Override
	public String toString() {
		return "BankAccountDto [accountId=" + accountId + ", customerId=" + customerId + ", accountType=" + accountType
				+ ", currentBalance=" + currentBalance + ", accountStatus=" + accountStatus + ", wealth=" + wealth
				+ ", wealthUnit=" + wealthUnit + ", businessType=" + businessType + ", totalRevenue=" + totalRevenue
				+ ", officeAddress=" + officeAddress + "]";
	}

	
	
	
	
	
	
}

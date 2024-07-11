package com.bank.transaction.entity;

import java.time.LocalDateTime;

import com.bank.transaction.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long transactionId;
	
	@Column(nullable = false)
	private Long accountId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Column(nullable = false)
	private Double transactionAmount;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime transactionTime;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	
	
}

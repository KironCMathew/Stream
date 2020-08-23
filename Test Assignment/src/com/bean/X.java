package com.bean;

import java.time.LocalDate;

public class X {
	private String transactionId;
	private String accountId;
	private LocalDate postingDate;
	private String amount;
	
	public X() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public LocalDate getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(LocalDate localDate) {
		this.postingDate = localDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "DataX [transactionId=" + transactionId + ", accountId=" + accountId + ", postingDate=" + postingDate
				+ ", amount=" + amount + "]";
	}
	
}

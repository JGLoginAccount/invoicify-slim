package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;

@Entity
public class FlatFeeBillingRecord extends BillingRecord {
	

	public FlatFeeBillingRecord() {
		
	}
	public FlatFeeBillingRecord(String description, User createdBy, InvoiceLineItem lineItem, double amount) {
		super(description, createdBy, lineItem);
		this.amount=amount;
	}


	double amount;
	

	@Override
	public double getTotal() {
		return amount;
	}


	public double getAmount() {
		return amount;
	} 


	public void setAmount(double amount) {
		this.amount = amount;
	}
	

	
}

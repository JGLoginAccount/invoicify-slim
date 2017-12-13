package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;

@Entity
public class RateBasedBillingRecord extends BillingRecord {

	public RateBasedBillingRecord() {
		
	}

	
	public RateBasedBillingRecord(String description, User createdBy, 
			InvoiceLineItem lineItem,
			double rate, double quantity) {
		super(description, createdBy, lineItem);
		// TODO Auto-generated constructor stub
		this.rate=rate;
		this.quantity=quantity;
	}


	double rate;
	
	double quantity;
	
	
	@Override
	public double getTotal() {
		return rate*quantity;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}

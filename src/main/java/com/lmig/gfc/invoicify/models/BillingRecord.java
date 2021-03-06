package com.lmig.gfc.invoicify.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public abstract class BillingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;
	

	@ManyToOne
	private User createdBy;
 
	@OneToOne(mappedBy = "billingRecord")
	private InvoiceLineItem lineItem;

	@ManyToOne
	
	private Company client;

	public BillingRecord() {
		
	}
	
	public BillingRecord (String description, User createdBy, InvoiceLineItem lineItem) {
		this.description=description;
		this.createdBy=createdBy;
		this.lineItem=lineItem;
	}
	
	public abstract double getTotal();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public InvoiceLineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(InvoiceLineItem lineItem) {
		this.lineItem = lineItem;
	}

	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

}

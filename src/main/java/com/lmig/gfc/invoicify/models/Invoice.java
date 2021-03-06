package com.lmig.gfc.invoicify.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Company company;
	
	@ManyToOne
	private User createdBy;
	private String invoiceNumber;
	@OneToMany(mappedBy="invoice", cascade=CascadeType.ALL)
	List<InvoiceLineItem> invoices;
	
	public Invoice(Company company,  User createdBy,String invoiceNumber,List<InvoiceLineItem> invoices) {
		this.company=company;
		this.createdBy=createdBy;
		this.invoiceNumber=invoiceNumber;
		this.invoices=invoices;
		
		
	}
	
	public Invoice() {
		invoices = new ArrayList<InvoiceLineItem>();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public User getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public List<InvoiceLineItem> getInvoices() {
		return invoices;
	}


	public void setInvoices(List<InvoiceLineItem> invoices) {
		this.invoices = invoices;
	}



	


}

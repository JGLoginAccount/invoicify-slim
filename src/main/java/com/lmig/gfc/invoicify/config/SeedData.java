package com.lmig.gfc.invoicify.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordsRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;
import com.lmig.gfc.invoicify.services.UserRepository;

@Configuration
public class SeedData {

	public SeedData(UserRepository userRepository, PasswordEncoder encoder,CompanyRepository companyRepository,
			InvoiceRepository invoiceRepository,BillingRecordsRepository billingRecordsRepository) {
		String encodedPassword = encoder.encode("password");
		User user = new User();
		user.setUsername("admin");
		user.setPassword(encodedPassword);
		user.addRole("ADMIN");
		userRepository.save(user);
		
		encodedPassword = encoder.encode("password");
		user = new User();
		user.setUsername("clerk");
		user.setPassword(encodedPassword);
		user.addRole("CLERK");
		userRepository.save(user);
		
		encodedPassword = encoder.encode("password");
		user = new User();
		user.setUsername("accountant");
		user.setPassword(encodedPassword);
		user.addRole("ACCOUNTANT");
		userRepository.save(user);
		
		//Affiliated billing records to invoices
		
		FlatFeeBillingRecord FlatFeeBillingRecord = new FlatFeeBillingRecord ("TestOfSeedData",user,null,12);
		billingRecordsRepository.save(FlatFeeBillingRecord);
		Company company = new Company("ACME",null);
		companyRepository.save(company);
		InvoiceLineItem invoiceLineItem=new InvoiceLineItem (FlatFeeBillingRecord,user,null);
	
		ArrayList<InvoiceLineItem>invoiceLineItems=new ArrayList<InvoiceLineItem>();
		
		invoiceLineItems.add(invoiceLineItem);
		FlatFeeBillingRecord.setClient(company);
		
		Invoice invoice = new Invoice(company,user,"1A2B3C4D",invoiceLineItems);
		invoiceRepository.save(invoice);
		
		ArrayList<Invoice>invoices=new ArrayList<Invoice>();
		
		company.setInvoices(invoices);
		invoiceLineItem.setInvoice(invoice);
		FlatFeeBillingRecord.setLineItem(invoiceLineItem);
		companyRepository.save(company);
		invoiceRepository.save(invoice);
		billingRecordsRepository.save(FlatFeeBillingRecord);
		
		//Unaffiliated billing records
		
		FlatFeeBillingRecord FlatFeeBillingRecord2 = new FlatFeeBillingRecord ("TestOfSeedData",user,null,12);
		billingRecordsRepository.save(FlatFeeBillingRecord2);
		Company company2 = new Company("VeryLargeCorporationofamerica",null);
		companyRepository.save(company2);
	
		
		invoiceLineItems.add(invoiceLineItem);
		FlatFeeBillingRecord2.setClient(company2);

		companyRepository.save(company2);
		billingRecordsRepository.save(FlatFeeBillingRecord2);
	
	}

}

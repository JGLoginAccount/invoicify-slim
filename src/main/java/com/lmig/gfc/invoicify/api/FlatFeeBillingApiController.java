package com.lmig.gfc.invoicify.api;


import org.springframework.security.core.Authentication;  
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordsRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/flatfees")  

public class FlatFeeBillingApiController {

	   
	private CompanyRepository companyRepository;
	private BillingRecordsRepository billingyRepository;
	
	public FlatFeeBillingApiController(CompanyRepository companyRepository, BillingRecordsRepository billingyRepository) {
		this.billingyRepository=billingyRepository;
		this.companyRepository=companyRepository;
		 
	}

	@PostMapping("")
	public FlatFeeBillingRecord createUsingApi(@RequestBody FlatFeeBillingRecord billingRecord, 
			Authentication auth) 
	{
		
		
		User user=(User) auth.getPrincipal();	
		
		billingRecord.setClient(companyRepository.findOne(billingRecord.getClient().getId()));
	
		billingRecord.setCreatedBy(user);

		return billingyRepository.save(billingRecord);
		
	} 
	
}

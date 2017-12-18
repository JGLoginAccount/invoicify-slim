package com.lmig.gfc.invoicify.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.RateBasedBillingRecordRepository;

@RestController 
@RequestMapping("/api/ratefees")
public class RateBasedBillingRecordApiController {
 
	private CompanyRepository companyRepository;
	private RateBasedBillingRecordRepository rateBasedBillingRecordRepository;
	
	public RateBasedBillingRecordApiController (CompanyRepository companyRepository, 
			RateBasedBillingRecordRepository rateBasedBillingRecordRepository) {
		this.companyRepository=companyRepository;
		this.rateBasedBillingRecordRepository=rateBasedBillingRecordRepository;
	}

	@PostMapping("")
	public RateBasedBillingRecord createUsingApi(@RequestBody RateBasedBillingRecord billingRecord, 
			Authentication auth) {
		
		User user=(User) auth.getPrincipal();	
		
		billingRecord.setClient(companyRepository.findOne(billingRecord.getClient().getId()));
	
		billingRecord.setCreatedBy(user);

		return rateBasedBillingRecordRepository.save(billingRecord);
	}
	
}

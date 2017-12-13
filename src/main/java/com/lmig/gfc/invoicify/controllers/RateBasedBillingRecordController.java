package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.RateBasedBillingRecordRepository;

@Controller
@RequestMapping("/billing-records/rate-baseds")
public class RateBasedBillingRecordController {
	
	private CompanyRepository companyRepository;
	private RateBasedBillingRecordRepository rateBasedBillingRecordRepository;
	
	public RateBasedBillingRecordController (CompanyRepository companyRepository, RateBasedBillingRecordRepository rateBasedBillingRecordRepository) {
		this.companyRepository=companyRepository;
		this.rateBasedBillingRecordRepository=rateBasedBillingRecordRepository;
	}

	@PostMapping("")
	public ModelAndView create(RateBasedBillingRecord record, long clientId, Authentication auth) {
		
		User user=(User) auth.getPrincipal();
		
		Company company = companyRepository.findOne(clientId);
		
		
		record.setClient(company);
		record.setCreatedBy(user);
		
		
		rateBasedBillingRecordRepository.save(record);

		
		return new ModelAndView("redirect:/billing-records");
	}
	
}
